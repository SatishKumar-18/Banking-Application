package com.project.Banking.Application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetBankingRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
