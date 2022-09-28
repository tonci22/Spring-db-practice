package com.example.demo.service.Implementation;

import com.example.demo.domain.ReservationHistory;
import com.example.demo.dto.response.ReservationHistoryResponseDto;
import com.example.demo.enums.ReservationType;
import com.example.demo.mapper.ReservationHistoryMapper;
import com.example.demo.repository.ReservationHistoryRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationHistoryServiceImpl implements ReservationHistoryService {

    private final ReservationHistoryRepository reservationHistoryRepository;
    private final ReservationHistoryMapper reservationHistoryMapper;


    public ReservationHistoryServiceImpl(final ReservationHistoryRepository reservationHistoryRepository,
                                         final ReservationHistoryMapper reservationHistoryMapper) {
        this.reservationHistoryRepository = reservationHistoryRepository;
        this.reservationHistoryMapper = reservationHistoryMapper;
    }

    @Override
    public List<ReservationHistoryResponseDto> getAll() {
        return reservationHistoryMapper.mapToDto(reservationHistoryRepository.findAll());
    }

    @Override
    public void add(Long id, ReservationType oldReservationType, ReservationType newReservationType) {

        ReservationHistory reservationHistory = new ReservationHistory();

        reservationHistory.setReservationId(id);
        reservationHistory.setFromType(oldReservationType);
        reservationHistory.setToType(newReservationType);

        reservationHistoryRepository.save(reservationHistory);
    }
}
