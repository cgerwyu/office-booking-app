package ru.ald.officebooking.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.util.UUID;

@Entity
@Table(
    name="users",
    uniqueConstraints = {
        @UniqueConstraint(name="uq_email", columnNames="email")
    }
)
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @Column(nullable = false, length = 64)
    String name;

    @Column(nullable = false, length = 255)
    String email;

    @Column(nullable = false, length = 255)
    String password;
}
