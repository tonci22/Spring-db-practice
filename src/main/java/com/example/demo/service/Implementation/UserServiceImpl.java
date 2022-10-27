package com.example.demo.service.Implementation;


import com.example.demo.domain.Reservation;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.request.UserUpdateRequestDto;
import com.example.demo.enums.RoleType;
import com.example.demo.exception.RepositoryNotFoundException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReservationService reservationService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public User add(UserRequestDto userRequestDto) {

        Role role = roleRepository.findByName(RoleType.ROLE_USER.toString());
        User user = userMapper.mapToDto(userRequestDto);
        user.setRoles(List.of(role));
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("User id not found"));
        User partial = userMapper.mapToDto(userUpdateRequestDto);
        partial.setId(id);
        partial.setReservations(user.getReservations());
        return userRepository.save(partial);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("User id not found"));
        userRepository.deleteById(id);
    }



    @Override
    public User addReservation(Long id, Long idAccommodation, ReservationRequestDto reservationCreateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("User id not found"));
        Reservation reservation = reservationMapper.mapToDto(reservationCreateDto);
        user.getReservations().add(reservation);
        return userRepository.save(user);
    }

    @Override
    public User updateReservation(Long idLocation, Long idReservation, ReservationRequestDto reservationUpdateDto) {
        User user = userRepository.findById(idLocation).orElseThrow(() -> new RepositoryNotFoundException("Location id not found"));
        reservationService.getById(idReservation);

        reservationService.update(idReservation,reservationUpdateDto);

        return userRepository.save(user);
    }
}
