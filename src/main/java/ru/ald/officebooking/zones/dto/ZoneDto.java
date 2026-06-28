package ru.ald.officebooking.zones.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZoneDto {

    @NotBlank
    @Size(min = 3, max = 32)
    String name;

    @NotNull
    @Min(1)
    @Max(3)
    Short floor;
}
