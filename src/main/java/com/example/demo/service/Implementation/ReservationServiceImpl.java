package com.example.demo.service.Implementation;

import com.example.demo.domain.Reservation;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;
import com.example.demo.exception.RepositoryNotFoundException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(final ReservationRepository reservationRepository,
                                  final ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationResponseDto> getAll() {
        return reservationMapper.mapToDto(reservationRepository.findAll());
    }

    @Override
    public ReservationResponseDto add(ReservationRequestDto reservationRequestDto) {
        Reservation reservation = reservationMapper.mapToDto(reservationRequestDto);
        return reservationMapper.mapToDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponseDto update(Long id, ReservationRequestDto reservationRequestDto) {
        reservationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Reservation ID not found"));

        Reservation reservation = reservationMapper.mapToDto(reservationRequestDto);
        reservation.setId(id);

        return reservationMapper.mapToDto(reservationRepository.save(reservation));
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Reservation ID not found"));

        reservationRepository.deleteById(id);
    }
}
