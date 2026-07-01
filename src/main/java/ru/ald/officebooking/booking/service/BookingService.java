package ru.ald.officebooking.booking.service;

import org.springframework.data.domain.Page;
import ru.ald.officebooking.booking.dto.CreateBookingDto;
import ru.ald.officebooking.booking.dto.ResponseBookingDto;
import ru.ald.officebooking.booking.dto.UpdateBookingDto;

import java.util.UUID;

public interface BookingService {

    public ResponseBookingDto createBooking(CreateBookingDto dto);

    public ResponseBookingDto getBookingById(UUID id);

    public Page<ResponseBookingDto> getBookingsPage(int page, int size);

    public ResponseBookingDto updateBooking(UUID id, UpdateBookingDto dto);

    public void deleteBooking(UUID id);

}
