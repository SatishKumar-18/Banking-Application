package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.TransactionRequest;
import com.project.Banking.Application.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse saveTransaction(String accountNumber, TransactionRequest transactionRequest);
}
