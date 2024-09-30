package com.project.Banking.Application.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String country;
    @Indexed(unique = true)
    private String aadharNumber;
    @Indexed(unique = true)
    private String panNumber;
    private String email;
    private String phoneNumber;
    @Indexed(unique = true)
    private String accountNumber;
    private BigDecimal accountBalance;
    private String status;
    private LocalDateTime createdAt;

}
