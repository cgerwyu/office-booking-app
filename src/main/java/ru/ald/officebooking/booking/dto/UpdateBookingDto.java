package ru.ald.officebooking.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateBookingDto {

    LocalDateTime startsAt;

    LocalDateTime endsAt;

    UUID bookerId;

    UUID workspaceId;

}
