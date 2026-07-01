package ru.ald.officebooking.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ald.officebooking.booking.model.Booking;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    public Optional<Booking> getBookingById(UUID id);

}
