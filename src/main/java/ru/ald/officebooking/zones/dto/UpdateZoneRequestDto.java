package ru.ald.officebooking.zones.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UpdateZoneRequestDto {

    @Size(min = 3, max = 8)
    String name;

    @Min(1)
    Short floor;
}
