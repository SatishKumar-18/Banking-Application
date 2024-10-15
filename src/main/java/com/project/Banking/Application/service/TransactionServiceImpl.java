package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.TransactionInfo;
import com.project.Banking.Application.dto.TransactionRequest;
import com.project.Banking.Application.dto.TransactionResponse;
import com.project.Banking.Application.entity.Account;
import com.project.Banking.Application.entity.Transaction;
import com.project.Banking.Application.repository.TransactionRepository;
import com.project.Banking.Application.utils.TransactionType;
import com.project.Banking.Application.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountService accountService;

    @Override
    public TransactionResponse saveTransaction(String accountNumber, TransactionRequest transactionRequest) {
        Account account = accountService.getAccount(accountNumber);

        if(account != null){
            Transaction transaction = Transaction.builder()
                    .transactionDate(LocalDateTime.now())
                    .recipientAccountNumber(transactionRequest.getRecipientAccountNumber())
                    .transactionType(transactionRequest.getTransactionType())
                    .amount(transactionRequest.getAmount())
                    .build();

            if(transaction.getTransactionType().equals(TransactionType.CREDIT)){
                transaction.setCurrentBalance(
                        account.getAccountBalance().add(transaction.getAmount())
                );
            }else if(transaction.getTransactionType().equals(TransactionType.DEBIT)){
                if(account.getAccountBalance().compareTo(transaction.getAmount()) > 0){
                    transaction.setCurrentBalance(
                            account.getAccountBalance().subtract(transaction.getAmount())
                    );
                }else{
                    return TransactionResponse.builder()
                            .responseCode(TransactionUtils.NO_SUFFICIENT_AMOUNT_CODE)
                            .responseMessage(TransactionUtils.NO_SUFFICIENT_AMOUNT_MESSAGE)
                            .build();
                }

            }

            Transaction savedTransaction = transactionRepo.save(transaction);

            account.getTransaction().add(savedTransaction);
            if(savedTransaction.getTransactionType().equals(TransactionType.CREDIT)){
                account.setAccountBalance(
                        account.getAccountBalance().add(savedTransaction.getAmount())
                );
            }else if(savedTransaction.getTransactionType().equals(TransactionType.DEBIT)){
                account.setAccountBalance(
                        account.getAccountBalance().subtract(savedTransaction.getAmount())
                );
            }

            accountService.saveAccount(account);

            return TransactionResponse.builder()
                    .responseCode(TransactionUtils.TRANSACTION_SUCCESSFUL_CODE)
                    .responseMessage(TransactionUtils.TRANSACTION_SUCCESSFUL_MESSAGE)
                    .transactionInfo(TransactionInfo.builder()
                            .transactionDate(savedTransaction.getTransactionDate())
                            .transactionType(savedTransaction.getTransactionType())
                            .amount(savedTransaction.getAmount())
                            .total_amount(account.getAccountBalance())
                            .build())
                    .build();
        }
        return TransactionResponse.builder()
                .responseCode(TransactionUtils.TRANSACTION_FAILED_CODE)
                .responseMessage(TransactionUtils.TRANSACTION_FAILED_MESSAGE)
                .build();
    }
}
