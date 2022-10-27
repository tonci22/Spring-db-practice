package com.example.demo.controller;


import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.request.UserUpdateRequestDto;
import com.example.demo.dto.response.ReservationResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.Implementation.ReservationServiceImpl;
import com.example.demo.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserServiceImpl userService;

    private final ReservationServiceImpl reservationService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReservationMapper reservationMapper;

    public UserController(@Qualifier("userServiceImpl") UserServiceImpl userService,
                          @Qualifier("reservationServiceImpl") ReservationServiceImpl reservationService) {

        this.userService = userService;
        this.reservationService = reservationService;
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return ResponseEntity.ok(userMapper.mapToDto(userService.getAll()));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.mapToDto(userService.add(userCreateDto)));
    }


    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto userUpdateDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userMapper.mapToDto(userService.updateUser(id, userUpdateDto)));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("DELETED USER");
    }



    @GetMapping(value = "/reservations")
    public ResponseEntity<List<ReservationResponseDto>> getReservations(){
        return ResponseEntity.ok(reservationMapper.mapToDto(reservationService.getAllEntities()));
    }

    @PostMapping(value = "/{id}/reservations/{idAccommodation}")
    public ResponseEntity<UserResponseDto> createReservation(@PathVariable("id") Long id,
                                                             @PathVariable("idAccommodation") Long idAccommodation,
                                                             @RequestBody ReservationRequestDto reservationCreateDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.
                mapToDto(userService.addReservation(id, idAccommodation,reservationCreateDto)));
    }

    @PutMapping("/{id}/reservations/{idReservation}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> updateReservation(@PathVariable("id") Long id, @PathVariable("idReservation") Long idReservation, @RequestBody ReservationRequestDto reservationUpdateDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userMapper.mapToDto(userService.updateReservation(id,idReservation,reservationUpdateDto)));
    }


    @DeleteMapping("/{id}/reservations/{idReservation}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return ResponseEntity.ok("DELETED RESERVATION");
    }
}
