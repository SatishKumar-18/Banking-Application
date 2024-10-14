package com.project.Banking.Application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInfo {
    private String employeeName;
    private String username;
    private String password;
}
