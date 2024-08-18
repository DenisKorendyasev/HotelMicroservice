package com.example.HotelMicroservice.domain.mapper;

import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MappedRoom {

    RoomDto toDto(Room room);
    Room toEntity(RoomDto roomDto);
}
