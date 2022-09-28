package com.example.demo.service;

import com.example.demo.dto.response.ReservationHistoryResponseDto;
import com.example.demo.enums.ReservationType;

import java.util.List;

public interface ReservationHistoryService {

    List<ReservationHistoryResponseDto> getAll();

    void add(Long id, ReservationType oldReservationType, ReservationType newReservationType);
}
