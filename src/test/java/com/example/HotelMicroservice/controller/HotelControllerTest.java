package com.example.HotelMicroservice.controller;

import com.example.HotelMicroservice.conroler.HotelController;
import com.example.HotelMicroservice.domain.dto.HotelDto;
import com.example.HotelMicroservice.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

    @Mock
    private HotelServiceImpl hotelService;

    @InjectMocks
    private HotelController hotelController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(hotelController)
                .build();
    }

    @Test
    public void testGetHotelById_HotelExists() throws Exception {
        UUID hotelId = UUID.randomUUID();
        HotelDto hotelDto = new HotelDto();
        when(hotelService.getById(hotelId)).thenReturn(Optional.of(hotelDto));

        mockMvc.perform(get("/hotels/{hotelId}", hotelId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

        @Test
    public void testGetAll() throws Exception {
        HotelDto hotel1 = new HotelDto();
        HotelDto hotel2 = new HotelDto();
        when(hotelService.getAll()).thenReturn(Arrays.asList(hotel1, hotel2));

        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testCreateHotel() throws Exception {
        HotelDto hotelDto = new HotelDto();
        String jsonContent = "{ \"name\": \"Azimut\", \"address\": \"Ленина\" }";

        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());
        verify(hotelService, times(1)).create(any(HotelDto.class));
    }

    @Test
    public void testUpdateHotel() throws Exception {
        UUID hotelId = UUID.randomUUID();
        HotelDto hotelDto = new HotelDto();
        String jsonContent = "{ \"name\": \"Azimut\", \"address\": \"Ленина\" }";

        mockMvc.perform(put("/hotels/{hotelId}", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
        verify(hotelService, times(1)).update(eq(hotelId), any(HotelDto.class));
    }

    @Test
    public void testDeleteHotel() throws Exception {
        UUID hotelId = UUID.randomUUID();

        mockMvc.perform(delete("/hotels/{hotelId}", hotelId))
                .andExpect(status().isNoContent());
        verify(hotelService, times(1)).delete(hotelId);
    }
}
