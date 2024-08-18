package com.example.HotelMicroservice.domain.dto;

import com.example.HotelMicroservice.domain.dto.enums.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private UUID id;
    private Integer number;
    private Integer price;
    private TypeDto type;

}
