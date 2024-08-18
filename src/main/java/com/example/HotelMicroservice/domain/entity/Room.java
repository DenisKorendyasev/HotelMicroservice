package com.example.HotelMicroservice.domain.entity;

import com.example.HotelMicroservice.domain.entity.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "price")
    private Integer price;

}
