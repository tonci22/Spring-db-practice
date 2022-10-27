package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Implementation.UserServiceImpl;
import com.example.demo.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    UserServiceImpl userService;
    ReservationService reservationService;
    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    UserMapper userMapper;
    ReservationMapper reservationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(userRepository, reservationService, roleRepository, userMapper, reservationMapper);
    }

    @Test
    void getAll() {
        User user = new User("test", "test1", "email_test");
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(1,userService.getAll().size());
    }
}
