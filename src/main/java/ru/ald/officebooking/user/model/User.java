package ru.ald.officebooking.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(name="uq_user_email", columnNames="email")
})
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable=false, length=32)
    String name;

    @Column(nullable=false, length=64, unique = true)
    String email;

    @Column(nullable = false, length=128)
    UUID password;
}
