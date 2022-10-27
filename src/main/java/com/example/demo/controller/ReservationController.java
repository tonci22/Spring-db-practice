package com.example.demo.controller;

import com.example.demo.configuration.SwaggerConfig;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@Api(tags = {SwaggerConfig.RESERVATIONCONTROLLERTAG})
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(@Qualifier("reservationServiceImpl") final ReservationService reservationService,
                                 final ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @ApiOperation("Get all Reservations")
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAll());
    }

    @ApiOperation("Add Reservation")
    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.add(reservationRequestDto));
    }

    @ApiOperation("Update Reservation")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservationService.update(id, reservationRequestDto));
    }

    @ApiOperation("Delete Reservation")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("DELETED");
    }
}
