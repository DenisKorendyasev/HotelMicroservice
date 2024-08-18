package com.example.HotelMicroservice.domain.exception;

public class NotFoundHotelException extends RuntimeException{

    public NotFoundHotelException(String message) {
        super(message);
    }
}
