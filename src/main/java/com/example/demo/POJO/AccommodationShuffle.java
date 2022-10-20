package com.example.demo.POJO;

import com.example.demo.dto.response.AccommodationResponseDto;

import java.util.*;

public class AccommodationShuffle {

    public static List<AccommodationResponseDto> getAccommodationsShuffled(List<AccommodationResponseDto> accommodations){
        if(accommodations.size() < 10)
            return null;

        Set<AccommodationResponseDto> shuffledAccommodations = new HashSet<>();
        Random random = new Random();

        while (shuffledAccommodations.size() < 10){
            shuffledAccommodations.add(accommodations.get(random.nextInt(accommodations.size())));
        }

        return new ArrayList<>(shuffledAccommodations);
    }
}
