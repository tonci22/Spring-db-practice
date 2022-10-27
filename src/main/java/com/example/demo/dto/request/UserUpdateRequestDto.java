package com.example.demo.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequestDto {

    String firstName;
    String lastName;
    String email;
    String password;
    boolean enabled;
    boolean tokenExpired;
}
