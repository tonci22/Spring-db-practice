package com.example.demo.mapper;

import com.example.demo.domain.ReservationHistory;
import com.example.demo.dto.request.ReservationHistoryRequestDto;
import com.example.demo.dto.response.ReservationHistoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationHistoryMapper {

    List<ReservationHistoryResponseDto> mapToDto(List<ReservationHistory> reservationHistories);
    ReservationHistoryResponseDto mapToDto(ReservationHistory reservationHistory);
    ReservationHistoryResponseDto mapToDto(ReservationHistoryRequestDto reservationHistoryRequestDto);
}
