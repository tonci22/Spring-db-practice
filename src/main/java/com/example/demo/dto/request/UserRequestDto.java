package com.example.demo.dto.request;


import com.example.demo.dto.response.RoleResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    private List<ReservationRequestDto> reservations = new ArrayList<>();
    private List<RoleResponseDto> roles = new ArrayList<>();

    public UserRequestDto() {
    }

    public UserRequestDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
