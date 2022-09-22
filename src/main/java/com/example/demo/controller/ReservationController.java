package com.example.demo.controller;

import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(@Qualifier("reservationServiceImpl") final ReservationService reservationService,
                                 final ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAll());
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.add(reservationRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservationService.update(id, reservationRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("DELETED");
    }
}
