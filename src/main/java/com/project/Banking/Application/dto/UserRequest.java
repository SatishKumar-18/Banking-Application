package com.project.Banking.Application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NonNull
    private String name;
    @NonNull
    private String gender;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String country;
    @NonNull
    private String aadharNumber;
    @NonNull
    private String panNumber;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
    private String status;
}
