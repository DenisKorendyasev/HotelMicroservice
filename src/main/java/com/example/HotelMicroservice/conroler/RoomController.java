package com.example.HotelMicroservice.conroler;

import com.example.HotelMicroservice.domain.dto.RoomDto;
import com.example.HotelMicroservice.domain.exception.NotFoundHotelException;
import com.example.HotelMicroservice.service.RoomService;
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
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDto> getById(@PathVariable UUID roomId) {
        RoomDto roomDto = roomService.getById(roomId)
                .orElseThrow(() -> new NotFoundHotelException
                        (MessageFormat.format("Комната с ID: {id} не найден", roomId)));
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAll() {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{hotelId}/rooms")
    public ResponseEntity create(@PathVariable UUID hotelId, @RequestBody RoomDto roomDto) {
        roomService.create(hotelId, roomDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity update(@PathVariable UUID hotelId,
                                 @PathVariable UUID roomId,
                                 @RequestBody RoomDto roomDto) {
        roomService.update(hotelId, roomId, roomDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity deleteById(@PathVariable UUID roomId) {
        roomService.delete(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
