package ru.ald.officebooking.booking.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.ald.officebooking.user.model.User;

@Data
@Entity
@Table(name="bookings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer time;

    @Column(nullable = false)
    User user;
}
