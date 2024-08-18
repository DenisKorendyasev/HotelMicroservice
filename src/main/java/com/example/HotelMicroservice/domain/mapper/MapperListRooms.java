package com.example.HotelMicroservice.domain.mapper;

import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING, uses = {MappedRoom.class})
@Component
public interface MapperListRooms {
    List<RoomDto> toDtoList(List<Room> rooms);
    List<Room> toEntityList(List<RoomDto> roomsDto);
}
