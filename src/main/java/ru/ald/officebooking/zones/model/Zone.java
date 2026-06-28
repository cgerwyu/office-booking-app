package ru.ald.officebooking.zones.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(
    name = "zones",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_zone_name", columnNames = "name")
    }
)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    Short floor;

    public Zone(String name, Short floor) {
        this.name = name;
        this.floor = floor;
    }

}
