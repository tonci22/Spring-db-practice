package com.example.demo.service.Implementation;

import com.example.demo.domain.Location;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.LocationResponseDto;
import com.example.demo.exception.RepositoryNotFoundException;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(final LocationRepository locationRepository, final LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public LocationResponseDto add(LocationRequestDto locationRequestDto) {

        Location location = locationMapper.mapToDto(locationRequestDto);

        return locationMapper.mapToDto(locationRepository.save(location));
    }

    @Override
    public LocationResponseDto update(Long id, LocationRequestDto locationRequestDto) {

        locationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Location ID not found"));

        Location location = locationMapper.mapToDto(locationRequestDto);
        location.setId(id);

        locationRepository.save(location);

        return locationMapper.mapToDto(location);
    }

    @Override
    public boolean getById(Long id) {
        return locationRepository.findById(id).isPresent();

    }

    @Override
    public void deleteById(Long id) {
        locationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Location ID not found"));
        locationRepository.deleteById(id);
    }
}
