package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LocationRequestDto {
    private Long id;
    private Integer postalCode;
    private String title;
    private List<AccommodationRequestDto> accommodations = new ArrayList<>();
}
