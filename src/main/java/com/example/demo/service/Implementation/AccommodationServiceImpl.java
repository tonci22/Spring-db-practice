package com.example.demo.service.Implementation;

import com.example.demo.domain.Accommodation;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.exception.RepositoryNotFoundException;
import com.example.demo.mapper.AccommodationMapper;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.service.AccommodationService;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final LocationService locationService;

    public AccommodationServiceImpl(final AccommodationRepository accommodationRepository,
                                    final AccommodationMapper accommodationMapper,
                                    @Qualifier("locationServiceImpl") final LocationService locationService) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
        this.locationService = locationService;
    }

    @Override
    public AccommodationResponseDto add(AccommodationRequestDto accommodationRequestDto) {

        if(accommodationRequestDto.getLocation().getId() == null)
            throw new RepositoryNotFoundException("Location ID not found");

        Accommodation accommodation = accommodationMapper.mapToDto(accommodationRequestDto);

        if(locationService.getById(accommodationRequestDto.getLocation().getId()) == null)
            locationService.add(accommodationRequestDto.getLocation());

        return accommodationMapper.mapToDto(accommodationRepository.save(accommodation));
    }

    @Override
    public List<Accommodation> getAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public AccommodationResponseDto update(Long id, AccommodationRequestDto accommodationRequestDto) {

        accommodationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Accommodation ID not found"));
        Accommodation accommodation = accommodationMapper.mapToDto(accommodationRequestDto);
        accommodation.setId(id);

        return accommodationMapper.mapToDto(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Accommodation ID not found"));
        accommodationRepository.deleteById(id);
    }
}