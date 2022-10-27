package com.example.demo.mapper;

import com.example.demo.domain.Role;
import com.example.demo.dto.response.RoleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponseDto mapToDto(Role role);
}
