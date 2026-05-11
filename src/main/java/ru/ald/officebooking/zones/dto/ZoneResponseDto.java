package ru.ald.officebooking.zones.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ZoneResponseDto {

    UUID id;

    String name;

    Short floor;

    public ZoneResponseDto(UUID id, String name, Short floor) {
        this.id = id;
        this.name = name;
        this.floor = floor;
    }
}
