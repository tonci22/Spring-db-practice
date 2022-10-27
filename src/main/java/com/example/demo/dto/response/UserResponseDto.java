package com.example.demo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    private List<RoleResponseDto> roles;
    private List<ReservationResponseDto> reservations;
}
