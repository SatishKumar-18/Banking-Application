package com.project.Banking.Application.dto;

import com.project.Banking.Application.utils.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInfo {
    private LocalDateTime transactionDate;
    private String Description;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal total_amount;
}
