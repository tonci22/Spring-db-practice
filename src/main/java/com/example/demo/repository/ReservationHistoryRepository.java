package com.example.demo.repository;

import com.example.demo.domain.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoryRepository  extends JpaRepository<ReservationHistory, Long> {
}
