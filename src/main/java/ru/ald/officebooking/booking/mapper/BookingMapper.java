package ru.ald.officebooking.booking.mapper;

import ru.ald.officebooking.booking.dto.CreateBookingDto;
import ru.ald.officebooking.booking.dto.ResponseBookingDto;
import ru.ald.officebooking.booking.model.Booking;
import ru.ald.officebooking.user.model.User;
import ru.ald.officebooking.workspace.model.Workspace;

import java.util.UUID;

public class BookingMapper {

    public Booking mapCreateDtoToBooking(CreateBookingDto dto, User booker, Workspace workspace) {
        Booking booking = new Booking();

        booking.setStartsAt(dto.getStartsAt());
        booking.setEndsAt(dto.getEndsAt());
        booking.setBooker(booker);
        booking.setWorkspace(workspace);

        return booking;
    }

    public ResponseBookingDto mapBookingToResponseDto(Booking booking) {
        return new ResponseBookingDto(
            booking.getId(),
            booking.getStartsAt(),
            booking.getEndsAt(),
            booking.getBooker().getId(),
            booking.getWorkspace().getId()
        );
    }

}
