package com.example.demo.service;

import com.example.demo.domain.Accommodation;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;

import java.util.List;

public interface AccommodationService {

    AccommodationResponseDto add(AccommodationRequestDto accommodationRequestDto);
    List<Accommodation> getAll();
    AccommodationResponseDto update(Long id, AccommodationRequestDto accommodationRequestDto);
    void deleteById(Long id);
}
