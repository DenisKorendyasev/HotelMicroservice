package com.example.HotelMicroservice.service;

import com.example.HotelMicroservice.domain.dto.HotelDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelService {

    Optional<HotelDto> getById(UUID id);
    List<HotelDto> getAll();
    void create(HotelDto hotelDto);
    void update(UUID id, HotelDto hotelDto);
    void delete(UUID id);
}
