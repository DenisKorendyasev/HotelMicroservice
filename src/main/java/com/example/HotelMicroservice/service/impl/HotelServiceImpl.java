package com.example.HotelMicroservice.service.impl;

import com.example.HotelMicroservice.domain.dto.HotelDto;
import com.example.HotelMicroservice.domain.mapper.MappedHotel;
import com.example.HotelMicroservice.repository.HotelRepository;
import com.example.HotelMicroservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final MappedHotel mappedHotel;
    private final HotelRepository hotelRepository;

    @Override
    public Optional<HotelDto> getById(UUID id) {
        return Optional.ofNullable(
                mappedHotel.toDto(hotelRepository.getById(id)));
    }

    @Override
    public List<HotelDto> getAll() {
        return hotelRepository.findAll()
                .stream()
                .map(mappedHotel::toDto)
                .toList();
    }

    @Override
    public void create(HotelDto hotelDto) {
        hotelRepository.save(
                mappedHotel.toEntity(hotelDto));
    }

    @Override
    public void update(UUID id, HotelDto hotelDto) {
        if (hotelRepository.existsById(id)) {
            hotelDto.setId(id);
            hotelRepository.save(mappedHotel.toEntity(hotelDto));
        }
    }

    @Override
    public void delete(UUID id) {
        hotelRepository.deleteById(id);
    }
}
