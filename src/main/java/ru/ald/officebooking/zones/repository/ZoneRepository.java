package ru.ald.officebooking.zones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ald.officebooking.zones.model.Zone;

import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    boolean existsByName(String name);

}
