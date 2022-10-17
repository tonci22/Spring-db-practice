package com.example.demo.dto.response;

import com.example.demo.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccommodationResponseDto {
    private Long id;
    private String title;
    private String subtitle;
    private Integer categorization;
    private Integer personCount;
    private Byte[] image;
    private boolean freeCancellation = true;
    private double price;
    private AccommodationType type;
    private LocationResponseDto location;
}
