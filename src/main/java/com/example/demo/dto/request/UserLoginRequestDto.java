package com.example.demo.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
