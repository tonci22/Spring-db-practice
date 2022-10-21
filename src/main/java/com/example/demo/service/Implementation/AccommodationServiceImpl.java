package com.example.demo.service.Implementation;

import com.example.demo.domain.Accommodation;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.response.AccommodationResponseDto;
import com.example.demo.exception.RepositoryNotFoundException;
import com.example.demo.mapper.AccommodationMapper;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.service.AccommodationService;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
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

        if(!locationService.getById(accommodationRequestDto.getLocation().getId()))
            locationService.add(accommodationRequestDto.getLocation());

        return accommodationMapper.mapToDto(accommodationRepository.save(accommodation));
    }

    @Override
    public List<AccommodationResponseDto> getAll() {
        return accommodationMapper.mapToDto(accommodationRepository.findAll());
    }

    @Override
    public AccommodationResponseDto update(Long id, AccommodationRequestDto accommodationRequestDto) {

        accommodationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Accommodation ID not found"));

        if(!locationService.getById(accommodationRequestDto.getLocation().getId()))
            throw new DataIntegrityViolationException("Location ID not found");

        Accommodation accommodation = accommodationMapper.mapToDto(accommodationRequestDto);
        accommodation.setId(id);

        return accommodationMapper.mapToDto(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Accommodation ID not found"));
        accommodationRepository.deleteById(id);
    }

    @Override
    public List<AccommodationResponseDto> getCategorizationAndMinimumBeds(Integer stars, Integer minimumBeds) {
        return accommodationMapper.mapToDto(accommodationRepository.findByCategorizationEqualsAndPersonCountGreaterThanEqual(stars, minimumBeds));
    }

    @Override
    public List<AccommodationResponseDto> getShuffledAccommodations() {

        Set<AccommodationResponseDto> shuffledAccommodations = new HashSet<>();
        Random random = new Random();

        while (shuffledAccommodations.size() < 10){
            AccommodationResponseDto randomAccommodation = accommodationMapper.mapToDto(accommodationRepository
                    .findAll().get(random.nextInt(accommodationRepository.findAll().size())));

            shuffledAccommodations.add(randomAccommodation);
        }

        return new ArrayList<>(shuffledAccommodations);
    }
}
