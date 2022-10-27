package com.example.demo.controller;

import com.example.demo.configuration.SwaggerConfig;
import com.example.demo.dto.response.ReservationHistoryResponseDto;
import com.example.demo.service.ReservationHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservationHistory")
@Api(tags = {SwaggerConfig.RESERVATIONHISTORYCONTROLLERTAG})
public class ReservationHistoryController {

    private final ReservationHistoryService reservationHistoryService;

    public ReservationHistoryController(ReservationHistoryService reservationHistoryService) {
        this.reservationHistoryService = reservationHistoryService;
    }

    @ApiOperation("Get all Reservation History")
    @GetMapping
    public ResponseEntity<List<ReservationHistoryResponseDto>> getReservationHistory(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationHistoryService.getAll());
    }
}
