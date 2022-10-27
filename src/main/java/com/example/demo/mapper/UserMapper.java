package com.example.demo.mapper;


import com.example.demo.domain.User;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.request.UserUpdateRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToDto(UserRequestDto userRequestDto);
    User mapToDto(Long id, UserUpdateRequestDto userUpdateRequestDto);
    UserResponseDto mapToDto(User user);
    List<UserResponseDto> mapToDto(List<User> users);
    User mapToDto(UserUpdateRequestDto userUpdateRequestDto);
}
