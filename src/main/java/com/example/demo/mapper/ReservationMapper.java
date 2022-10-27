package com.example.demo.mapper;

import com.example.demo.domain.Reservation;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    List<ReservationResponseDto> mapToDto(List<Reservation> reservations);
    Reservation mapToDto(ReservationRequestDto reservationRequestDto);
    ReservationResponseDto mapToDto(Reservation reservation);
}
