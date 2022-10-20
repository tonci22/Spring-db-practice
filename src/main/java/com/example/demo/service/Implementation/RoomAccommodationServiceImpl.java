package com.example.demo.service.Implementation;

import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.service.AccommodationService;

import java.util.List;

public class RoomAccommodationServiceImpl implements AccommodationService {
    @Override
    public AccommodationResponseDto add(AccommodationRequestDto accommodationRequestDto) {
        return null;
    }

    @Override
    public List<AccommodationResponseDto> getAll() {
        return null;
    }

    @Override
    public AccommodationResponseDto update(Long id, AccommodationRequestDto accommodationRequestDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<AccommodationResponseDto> getCategorizationAndMinimumBeds(Integer stars, Integer minimumBeds) {
        return null;
    }

    @Override
    public List<AccommodationResponseDto> getShuffledAccommodations() {
        return null;
    }

}
