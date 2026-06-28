package ru.ald.officebooking.zones.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class ZoneResponseDto {

    UUID id;

    String name;

    Short floor;

}
