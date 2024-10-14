package com.project.Banking.Application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetBankingResponse {
    private String responseCode;
    private String responseMessage;
}
