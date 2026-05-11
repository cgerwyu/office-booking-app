package ru.ald.officebooking.zones.mapper;

import org.springframework.stereotype.Component;
import ru.ald.officebooking.zones.dto.ZoneResponseDto;
import ru.ald.officebooking.zones.model.Zone;

@Component
public class ZoneMapper {

    public ZoneResponseDto toZoneResponseDto(Zone zone) {
        return new ZoneResponseDto(
            zone.getId(),
            zone.getName(),
            zone.getFloor()
        );
    }
}
