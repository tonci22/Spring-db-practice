package com.example.demo.POJO;

import com.example.demo.dto.response.AccommodationResponseDto;

import java.util.*;

public class AccommodationShuffle {

    public static List<AccommodationResponseDto> getAccommodationsShuffled(List<AccommodationResponseDto> accommodations){
        if(accommodations.size() < 10)
            return null;

        Set<AccommodationResponseDto> shuffledAccommodations = new HashSet<>(accommodations);

        return new ArrayList<>(shuffledAccommodations);
    }
}
