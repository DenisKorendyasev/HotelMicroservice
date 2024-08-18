package com.example.HotelMicroservice.controller;

import com.example.HotelMicroservice.conroler.RoomController;
import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController)
                .build();
    }

    @Test
    public void testGetById_RoomExists() throws Exception {
        UUID roomId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        when(roomService.getById(roomId)).thenReturn(Optional.of(roomDto));

        mockMvc.perform(get("/hotels/rooms/{roomId}", roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testGetAll() throws Exception {
        RoomDto room1 = new RoomDto();
        RoomDto room2 = new RoomDto();
        when(roomService.getAll()).thenReturn(Arrays.asList(room1, room2));

        mockMvc.perform(get("/hotels/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testCreate() throws Exception {
        UUID hotelId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        String jsonContent = objectMapper.writeValueAsString(roomDto);

        mockMvc.perform(post("/hotels/{hotelId}/rooms", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());
        verify(roomService, times(1)).create(eq(hotelId), any(RoomDto.class));
    }

    @Test
    public void testUpdate() throws Exception {
        UUID hotelId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        RoomDto roomDto = new RoomDto();
        String jsonContent = objectMapper.writeValueAsString(roomDto);

        mockMvc.perform(put("/hotels/{hotelId}/rooms/{roomId}", hotelId, roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
        verify(roomService, times(1)).update(eq(hotelId), eq(roomId), any(RoomDto.class));
    }

    @Test
    public void testDelete() throws Exception {
        UUID roomId = UUID.randomUUID();

        mockMvc.perform(delete("/hotels/rooms/{roomId}", roomId))
                .andExpect(status().isNoContent());
        verify(roomService, times(1)).delete(roomId);
    }
}
