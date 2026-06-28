package ru.ald.officebooking.zones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ald.officebooking.zones.model.Zone;

import java.util.Optional;
import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    boolean existsByName(String name);

    @Query("select z.name from Zone z where z.id = :id")
    Optional<String> findZoneNameById(UUID id);
}
