package com.project.Banking.Application.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private ObjectId id;
    private String accountType;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String status;
    private LocalDateTime createdAt;

    @DBRef
    private List<Transaction> transaction = new ArrayList<>();
}
