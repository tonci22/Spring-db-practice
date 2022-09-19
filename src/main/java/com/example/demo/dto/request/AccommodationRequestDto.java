package com.example.demo.dto.request;

import com.example.demo.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationRequestDto {
    private Long id;
    private String title;
    private String subtitle;
    private Integer categorization;
    private Integer personCount;
    private Byte[] image;
    private boolean freeCancelation = true;
    private double price;
    private AccommodationType type;
    private LocationRequestDto location;
}
