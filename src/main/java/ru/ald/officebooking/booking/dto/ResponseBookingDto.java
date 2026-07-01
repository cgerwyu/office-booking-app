package ru.ald.officebooking.booking.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResponseBookingDto {

    UUID id;

    LocalDateTime startsAt;

    LocalDateTime endsAt;

    UUID bookerId;

    UUID workspaceId;

}
