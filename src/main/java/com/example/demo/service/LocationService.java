package com.example.demo.service;

import com.example.demo.domain.Location;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.LocationResponseDto;

import java.util.List;

public interface LocationService {

    List<Location> getAll();

    LocationResponseDto add(LocationRequestDto location);

    LocationResponseDto update(Long id, LocationRequestDto location);

    boolean getById(Long id);

    void deleteById(Long id);
}
