package ru.ald.officebooking.zones.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.ald.officebooking.zones.dto.ZoneDto;
import ru.ald.officebooking.zones.dto.UpdateZoneRequestDto;
import ru.ald.officebooking.zones.dto.ZoneResponseDto;
import ru.ald.officebooking.zones.service.ZoneService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.UUID;

import static ru.ald.officebooking.common.ApiPaths.ID_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping("/new")
    public ZoneResponseDto createZone(
            @Valid @RequestBody ZoneDto zoneDto
    ) {
        return zoneService.createZone(zoneDto);
    }

    @GetMapping
    public Page<ZoneResponseDto> getZones(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        return zoneService.getZones(page, size);
    }

    @GetMapping(ID_PATH)
    public ZoneResponseDto getZone(@PathVariable UUID id) {
        return zoneService.getZone(id);
    }

    @PatchMapping(ID_PATH)
    public ZoneResponseDto updateZone(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateZoneRequestDto updateZoneRequestDto
    ) {
        return zoneService.updateZone(id, updateZoneRequestDto);
    }

    @DeleteMapping(ID_PATH)
    public void deleteZone(@PathVariable UUID id) {
        zoneService.deleteZone(id);
    }


}
