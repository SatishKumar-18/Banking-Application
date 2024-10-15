package com.project.Banking.Application.dto;

import com.project.Banking.Application.utils.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {
    @NonNull
    private String recipientAccountNumber;
    @NonNull
    private TransactionType transactionType;
    @NonNull
    private BigDecimal amount;
}
