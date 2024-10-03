package com.project.Banking.Application.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    @NonNull
    private String accountType;
    @NonNull
    private String accountNumber;
    @NonNull
    private BigDecimal accountBalance;
    private String status;
}
