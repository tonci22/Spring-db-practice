package com.example.demo.service;



import com.example.demo.domain.User;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.request.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    User add(UserRequestDto userRequestDto);
    List<User> getAll();
    User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteById(Long id);

    User addReservation(Long id, Long idAccommodation, ReservationRequestDto reservationCreateDto);
    User updateReservation(Long idLocation, Long idReservation, ReservationRequestDto reservationUpdateDto);
}
