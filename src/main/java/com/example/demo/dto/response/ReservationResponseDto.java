package com.example.demo.dto.response;

import com.example.demo.enums.ReservationType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationResponseDto {

    private Long id;
    private ReservationType reservationType;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Integer personsCount;
    private boolean submitted;

    private AccommodationResponseDto accommodation;
}
