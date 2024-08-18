package com.example.HotelMicroservice.service;

import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.domain.mapper.MappedRoom;
import com.example.HotelMicroservice.domain.entity.Hotel;
import com.example.HotelMicroservice.domain.entity.Room;
import com.example.HotelMicroservice.repository.HotelRepository;
import com.example.HotelMicroservice.repository.RoomRepository;
import com.example.HotelMicroservice.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private MappedRoom mappedRoom;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void testGetByIdRoomExists() {
        UUID roomId = UUID.randomUUID();
        Room room = new Room();
        RoomDto roomDto = new RoomDto();
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(mappedRoom.toDto(room)).thenReturn(roomDto);

        Optional<RoomDto> result = roomService.getById(roomId);
        assertEquals(roomDto, result.get());
    }

    @Test
    public void testGetByIdRoomDoesNotExist() {
        UUID roomId = UUID.randomUUID();
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        Optional<RoomDto> result = roomService.getById(roomId);
        assertFalse(result.isPresent(), "Room should not be present");
    }

    @Test
    public void testGetAll() {
        Room room1 = new Room();
        Room room2 = new Room();
        RoomDto roomDto1 = new RoomDto();
        RoomDto roomDto2 = new RoomDto();
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        when(mappedRoom.toDto(room1)).thenReturn(roomDto1);
        when(mappedRoom.toDto(room2)).thenReturn(roomDto2);

        List<RoomDto> result = roomService.getAll();
        assertEquals(2, result.size());
        assertEquals(roomDto1, result.get(0));
        assertEquals(roomDto2, result.get(1));
    }

    @Test
    public void testCreateRoom() {
        UUID hotelId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        Hotel hotel = new Hotel();
        Room room = new Room();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(mappedRoom.toEntity(roomDto)).thenReturn(room);

        roomService.create(hotelId, roomDto);
        assertTrue(hotel.getRooms().contains(room));
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testUpdateRoomExists() {
        UUID hotelId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        Room room = new Room();
        when(roomRepository.existsById(roomId)).thenReturn(true);
        when(mappedRoom.toEntity(roomDto)).thenReturn(room);

        roomService.update(hotelId, roomId, roomDto);
        assertEquals(roomId, roomDto.getId());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    public void testUpdateRoomDoesNotExist() {
        UUID hotelId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        when(roomRepository.existsById(roomId)).thenReturn(false);

        roomService.update(hotelId, roomId, roomDto);
        verify(roomRepository, never()).save(any(Room.class));
    }

    @Test
    public void testDeleteRoom() {
        UUID roomId = UUID.randomUUID();
        roomService.delete(roomId);
        verify(roomRepository, times(1)).deleteById(roomId);
    }
}
