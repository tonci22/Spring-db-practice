package com.example.demo.service;

import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.dto.response.LocationResponseDto;

import java.util.List;

public interface LocationService {

    List<LocationResponseDto> getAll();

    LocationResponseDto add(LocationRequestDto location);

    LocationResponseDto update(Long id, LocationRequestDto location);

    boolean idIsPresent(Long id);

    List<AccommodationResponseDto> getAccommodationsByLocationId(Long id);

    void deleteById(Long id);
}
