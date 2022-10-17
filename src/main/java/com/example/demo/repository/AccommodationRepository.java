package com.example.demo.repository;

import com.example.demo.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findByCategorizationEqualsAndPersonCountGreaterThanEqual(Integer stars, Integer minimumBeds);
}
