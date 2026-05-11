package ru.ald.officebooking.zones.service;

import org.springframework.data.domain.Page;
import ru.ald.officebooking.zones.dto.CreateZoneRequestDto;
import ru.ald.officebooking.zones.dto.UpdateZoneRequestDto;
import ru.ald.officebooking.zones.dto.ZoneResponseDto;

import java.util.UUID;

public interface ZoneService {

    public ZoneResponseDto createZone(CreateZoneRequestDto createZoneRequestDto);

    public Page<ZoneResponseDto> getZones(int page, int size);

    public ZoneResponseDto getZone(UUID id);

    public ZoneResponseDto updateZone(UUID id, UpdateZoneRequestDto updateZoneRequestDto);

    public void deleteZone(UUID id);
}
