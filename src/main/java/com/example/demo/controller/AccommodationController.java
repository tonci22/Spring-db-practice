package com.example.demo.controller;

import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.mapper.AccommodationMapper;
import com.example.demo.service.AccommodationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationMapper accommodationMapper;

    public AccommodationController(@Qualifier("accommodationServiceImpl") final AccommodationService accommodationService,
                                   final AccommodationMapper accommodationMapper) {

        this.accommodationService = accommodationService;
        this.accommodationMapper = accommodationMapper;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationResponseDto>> getAllAccommodations(){
        return ResponseEntity.status(HttpStatus.OK).body(accommodationService.getAll());
    }


    @PostMapping
    public ResponseEntity<AccommodationResponseDto> addAccommodation(@RequestBody AccommodationRequestDto accommodationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accommodationService.add(accommodationRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationResponseDto> updateAccommodation(@PathVariable("id") Long id, @RequestBody AccommodationRequestDto accommodationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accommodationService.update(id, accommodationRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccommodation(@PathVariable("id") Long id){
        accommodationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }
}
