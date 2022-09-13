package com.example.demo.dto.response;

import com.example.demo.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationResponseDto {
    private Long id;
    private Integer categorization;
    private Integer personCount;
    private Byte[] image;
    private boolean freeCancelation = true;
    private double price;
    private AccommodationType type;
    private LocationResponseDto location;
}
