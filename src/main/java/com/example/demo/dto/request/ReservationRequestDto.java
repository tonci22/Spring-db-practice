package com.example.demo.dto.request;

import com.example.demo.enums.ReservationType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationRequestDto {
    private Long id;
    private ReservationType reservationType;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Integer personsCount;
    private boolean submitted;

    private AccommodationRequestDto accommodation;
}
