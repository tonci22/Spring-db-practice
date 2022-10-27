package com.example.demo.repositories;

import com.example.demo.domain.Accommodation;
import com.example.demo.repository.AccommodationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccommodationRepositoryIT {
    @Autowired
    AccommodationRepository accommodationRepository;

    @Test
    void findByCategorizationAndPersonCountGreaterThanEqual() {
        List<Accommodation> accommodations = accommodationRepository.findByCategorizationEqualsAndPersonCountGreaterThanEqual(3, 5);

        assertEquals(1, accommodations.size());
    }
}
