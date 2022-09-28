package com.example.demo.dto.request;

import com.example.demo.enums.ReservationType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationHistoryRequestDto {

    private Long id;
    private Long reservationId;
    private Timestamp entryTimestamp;
    private ReservationType fromType;
    private ReservationType toType;
}