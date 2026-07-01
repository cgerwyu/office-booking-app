package ru.ald.officebooking.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateBookingDto {

    @NotNull
    LocalDateTime startsAt;

    @NotNull
    LocalDateTime endsAt;

    @NotNull
    UUID bookerId;

    @NotNull
    UUID workspaceId;

}
