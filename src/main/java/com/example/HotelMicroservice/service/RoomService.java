package com.example.HotelMicroservice.service;

import com.example.HotelMicroservice.domain.dto.RoomDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomService {

    Optional<RoomDto> getById(UUID id);
    List<RoomDto> getAll();
    void create(UUID id, RoomDto roomDto);
    void update(UUID id, UUID hotelId, RoomDto roomDto);
    void delete(UUID id);
}
