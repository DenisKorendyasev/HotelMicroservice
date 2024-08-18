package com.example.HotelMicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private UUID id;
    private String name;
    private String address;
    private List<RoomDto> rooms;
}
