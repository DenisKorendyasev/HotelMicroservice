package com.example.HotelMicroservice.service;

import com.example.HotelMicroservice.domain.dto.HotelDto;
import com.example.HotelMicroservice.domain.mapper.MappedHotel;
import com.example.HotelMicroservice.domain.entity.Hotel;
import com.example.HotelMicroservice.repository.HotelRepository;
import com.example.HotelMicroservice.service.impl.HotelServiceImpl;
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
public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private MappedHotel mappedHotel;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void testGetByIdHotelExists() {
        UUID hotelId = UUID.randomUUID();
        Hotel hotel = new Hotel();
        HotelDto hotelDto = new HotelDto();
        when(hotelRepository.getById(hotelId)).thenReturn(hotel);
        when(mappedHotel.toDto(hotel)).thenReturn(hotelDto);

        Optional<HotelDto> result = hotelService.getById(hotelId);
        assertTrue(result.isPresent(), "Hotel should be present");
        assertEquals(hotelDto, result.get());
    }

    @Test
    public void testGetByIdHotelDoesNotExist() {
        UUID hotelId = UUID.randomUUID();
        when(hotelRepository.getById(hotelId)).thenReturn(null);

        Optional<HotelDto> result = hotelService.getById(hotelId);
        assertFalse(result.isPresent(), "Hotel should not be present");
    }

    @Test
    public void testGetAll() {
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        HotelDto hotelDto1 = new HotelDto();
        HotelDto hotelDto2 = new HotelDto();
        when(hotelRepository.findAll()).thenReturn(List.of(hotel1, hotel2));
        when(mappedHotel.toDto(hotel1)).thenReturn(hotelDto1);
        when(mappedHotel.toDto(hotel2)).thenReturn(hotelDto2);

        List<HotelDto> result = hotelService.getAll();
        assertEquals(2, result.size());
        assertEquals(hotelDto1, result.get(0));
        assertEquals(hotelDto2, result.get(1));
    }

    @Test
    public void testCreateHotel() {
        HotelDto hotelDto = new HotelDto();
        Hotel hotel = new Hotel();
        when(mappedHotel.toEntity(hotelDto)).thenReturn(hotel);

        hotelService.create(hotelDto);
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testUpdateHotelExists() {
        UUID hotelId = UUID.randomUUID();
        HotelDto hotelDto = new HotelDto();
        Hotel hotel = new Hotel();
        when(hotelRepository.existsById(hotelId)).thenReturn(true);
        when(mappedHotel.toEntity(hotelDto)).thenReturn(hotel);

        hotelService.update(hotelId, hotelDto);
        assertEquals(hotelId, hotelDto.getId());
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testUpdateHotelDoesNotExist() {
        UUID hotelId = UUID.randomUUID();
        HotelDto hotelDto = new HotelDto();
        when(hotelRepository.existsById(hotelId)).thenReturn(false);

        hotelService.update(hotelId, hotelDto);
        verify(hotelRepository, never()).save(any(Hotel.class));
    }

    @Test
    public void testDeleteHotel() {
        UUID hotelId = UUID.randomUUID();

        hotelService.delete(hotelId);
        verify(hotelRepository, times(1)).deleteById(hotelId);
    }
}
