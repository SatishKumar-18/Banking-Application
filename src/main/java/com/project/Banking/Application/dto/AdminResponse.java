package com.project.Banking.Application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
    private String responseCode;
    private String responseMessage;
    private EmployeeInfo employeeInfo;
}
