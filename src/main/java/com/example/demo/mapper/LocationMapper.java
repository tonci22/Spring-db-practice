package com.example.demo.mapper;

import com.example.demo.domain.Location;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.response.LocationResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    List<LocationResponseDto> mapToDto(List<Location> locations);
    LocationResponseDto mapToDto(Location location);
    Location mapToDto(LocationRequestDto locationRequestDto);

    LocationRequestDto mapToDtoRequest(Location location);

    Location mapToDto(LocationResponseDto locationResponseDto);
}
