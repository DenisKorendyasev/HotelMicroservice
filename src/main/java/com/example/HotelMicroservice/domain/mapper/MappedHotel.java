package com.example.HotelMicroservice.domain.mapper;

import com.example.HotelMicroservice.domain.dto.HotelDto;
import com.example.HotelMicroservice.domain.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MappedHotel {

    HotelDto toDto(Hotel hotel);
    Hotel toEntity(HotelDto hotelDto);
}
