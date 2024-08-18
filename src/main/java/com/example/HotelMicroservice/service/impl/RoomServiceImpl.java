package com.example.HotelMicroservice.service.impl;

import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.domain.mapper.MappedRoom;
import com.example.HotelMicroservice.domain.entity.Hotel;
import com.example.HotelMicroservice.repository.HotelRepository;
import com.example.HotelMicroservice.repository.RoomRepository;
import com.example.HotelMicroservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final MappedRoom mappedRoom;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Optional<RoomDto> getById(UUID id) {
        return roomRepository.findById(id)
                .map(mappedRoom::toDto);
    }

    @Override
    public List<RoomDto> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(mappedRoom::toDto)
                .toList();
    }

    @Override
    public void create(UUID id, RoomDto roomDto) {
     Hotel hotel = hotelRepository.findById(id).get();
     hotel.getRooms().add(mappedRoom.toEntity(roomDto));
     hotelRepository.save(hotel);
    }

    @Override
    public void update(UUID hotelId, UUID id, RoomDto roomDto) {
        if (roomRepository.existsById(id)) {
            roomDto.setId(id);
            roomRepository.save(mappedRoom.toEntity(roomDto));
        }
    }

    @Override
    public void delete(UUID id) {
        roomRepository.deleteById(id);
    }
}
