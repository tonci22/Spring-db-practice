package com.example.demo.controller;

import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.LocationResponseDto;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    public LocationController(@Qualifier("locationServiceImpl") final LocationService locationService, final LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> getAllLocations() {
        return ResponseEntity.status(HttpStatus.OK).body(locationMapper.mapToDto(locationService.getAll()));
    }

    @PostMapping
    public ResponseEntity<LocationResponseDto> createLocation(@RequestBody LocationRequestDto locationRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.add(locationRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable("id") Long id){
        locationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }
}