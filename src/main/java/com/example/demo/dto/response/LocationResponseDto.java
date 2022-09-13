package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponseDto {
    private Long id;
    private Integer postalCode;
    private String title;
}
