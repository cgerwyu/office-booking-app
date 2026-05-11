package ru.ald.officebooking.zones.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @Column(nullable = false, unique = true, length = 8)
    String name;

    @Column(nullable = false)
    Short floor;

    public Zone(String name, Short floor) {
        this.name = name;
        this.floor = floor;
    }
}
