package com.example.demo.controller;

import com.example.demo.configuration.SwaggerConfig;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.mapper.AccommodationMapper;
import com.example.demo.service.AccommodationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
@Api(tags = {SwaggerConfig.ACCOMMODATIONCONTROLLERTAG})
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationMapper accommodationMapper;

    public AccommodationController(@Qualifier("accommodationServiceImpl") final AccommodationService accommodationService,
                                   final AccommodationMapper accommodationMapper) {

        this.accommodationService = accommodationService;
        this.accommodationMapper = accommodationMapper;
    }

    @ApiOperation("Get all Accommodations")
    @GetMapping
    public ResponseEntity<List<AccommodationResponseDto>> getAllAccommodations(){
        return ResponseEntity.status(HttpStatus.OK).body(accommodationService.getAll());
    }

    @ApiOperation("Shuffle 10 Accommodations")
    @GetMapping("/recommendation")
    public ResponseEntity<List<AccommodationResponseDto>> getShuffledAccommodations(){
        if(accommodationService.getAll().size() < 10)
            throw new DataIntegrityViolationException("Database must contain at least 10 accommodations");

        return ResponseEntity.status(HttpStatus.OK).body(accommodationService.getShuffledAccommodations());
    }

    @ApiOperation("Get Accommodations from a single Location ID")
    @GetMapping("/location")
    public ResponseEntity<List<AccommodationResponseDto>> getAccommodationsFromLocationId(@RequestParam Long locationId){
        return ResponseEntity.status(HttpStatus.OK).body(accommodationService.getAccommodationsFromLocationId(locationId));
    }

    @ApiOperation("Add Accommodation")
    @PostMapping
    public ResponseEntity<AccommodationResponseDto> addAccommodation(@RequestBody AccommodationRequestDto accommodationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accommodationService.add(accommodationRequestDto));
    }

    @ApiOperation("Update Accommodation")
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationResponseDto> updateAccommodation(@PathVariable("id") Long id, @RequestBody AccommodationRequestDto accommodationRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accommodationService.update(id, accommodationRequestDto));
    }

    @ApiOperation("Delete Accommodation")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccommodation(@PathVariable("id") Long id){
        accommodationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }
}
