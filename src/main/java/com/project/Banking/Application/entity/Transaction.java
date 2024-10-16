package com.project.Banking.Application.entity;

import com.project.Banking.Application.utils.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private ObjectId id;
    private LocalDateTime transactionDate;
    private String recipientAccountNumber;
    private String description;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal currentBalance;
}
