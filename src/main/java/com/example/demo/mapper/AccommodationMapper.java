package com.example.demo.mapper;

import com.example.demo.domain.Accommodation;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccommodationMapper {

    List<AccommodationResponseDto> mapToDtoResponse(List<Accommodation> accommodations);
    List<Accommodation> mapToDtoAccommodation(List<AccommodationRequestDto> accommodations);
    Accommodation mapToDto(AccommodationRequestDto accommodationRequestDto);

    AccommodationResponseDto mapToDto(Accommodation accommodation);
}
