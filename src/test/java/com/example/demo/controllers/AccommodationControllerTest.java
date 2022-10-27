package com.example.demo.controllers;

import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class AccommodationControllerTest {
    @Spy
    AccommodationService accommodationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMockMvc() throws Exception{

        List<AccommodationResponseDto> accommodations = new ArrayList<>();

        while (accommodations.size() < 10){
            accommodations.add(accommodationRandomizer());
        }

        when(accommodationService.getShuffledAccommodations()).thenReturn(accommodations);

        assertEquals(accommodations, accommodationService.getShuffledAccommodations());
    }

    private AccommodationResponseDto accommodationRandomizer(){
        Random random = new Random();
        AccommodationResponseDto accommodation = new AccommodationResponseDto();
        accommodation.setId(random.nextLong(10));

        return accommodation;
    }
}