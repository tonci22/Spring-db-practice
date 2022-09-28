package com.example.demo.controller;

import com.example.demo.dto.response.ReservationHistoryResponseDto;
import com.example.demo.service.ReservationHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservationHistory")
public class ReservationHistoryController {

    private final ReservationHistoryService reservationHistoryService;

    public ReservationHistoryController(ReservationHistoryService reservationHistoryService) {
        this.reservationHistoryService = reservationHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationHistoryResponseDto>> getReservationHistory(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationHistoryService.getAll());
    }
}
