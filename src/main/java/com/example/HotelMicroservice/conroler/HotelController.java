package com.example.HotelMicroservice.conroler;

import com.example.HotelMicroservice.domain.dto.HotelDto;
import com.example.HotelMicroservice.domain.exception.NotFoundHotelException;
import com.example.HotelMicroservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable UUID hotelId) {
        HotelDto hotelDto = hotelService.getById(hotelId)
                .orElseThrow(() -> new NotFoundHotelException(
                        MessageFormat.format("Отель с ID: {0} не найден", hotelId)));
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAll() {
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody HotelDto hotelDto) {
        hotelService.create(hotelDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity update(@PathVariable UUID hotelId, @RequestBody HotelDto hotelDto) {
        hotelService.update(hotelId, hotelDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{hotelId}")
    public ResponseEntity delete(@PathVariable UUID hotelId) {
        hotelService.delete(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
