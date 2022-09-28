package com.example.demo.service;

import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;

import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> getAll();
    ReservationResponseDto getById(Long id);
    ReservationResponseDto add(ReservationRequestDto reservationRequestDto);
    ReservationResponseDto update(Long id, ReservationRequestDto reservationRequestDto);
    void deleteById(Long id);
}
