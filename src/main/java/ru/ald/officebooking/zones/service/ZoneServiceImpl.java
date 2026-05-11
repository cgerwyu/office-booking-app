package ru.ald.officebooking.zones.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ald.officebooking.exception.AlreadyExistsException;
import ru.ald.officebooking.exception.NotFoundException;
import ru.ald.officebooking.exception.ValidationException;
import ru.ald.officebooking.zones.dto.CreateZoneRequestDto;
import ru.ald.officebooking.zones.dto.UpdateZoneRequestDto;
import ru.ald.officebooking.zones.dto.ZoneResponseDto;
import ru.ald.officebooking.zones.mapper.ZoneMapper;
import ru.ald.officebooking.zones.model.Zone;
import ru.ald.officebooking.zones.repository.ZoneRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Transactional
    public ZoneResponseDto createZone(CreateZoneRequestDto createZoneRequestDto) {
        String zoneName = createZoneRequestDto.getName();
        checkZoneNameIsAvailable(zoneName);

        Zone zone = new Zone(
            zoneName,
            createZoneRequestDto.getFloor()
        );

        Zone savedZone = zoneRepository.save(zone);

        return zoneMapper.toZoneResponseDto(savedZone);
    }

    @Transactional(readOnly = true)
    public Page<ZoneResponseDto> getZones(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Zone> zonesPage = zoneRepository.findAll(pageable);

        return zonesPage.map(zoneMapper::toZoneResponseDto);
    }

    @Transactional(readOnly = true)
    public ZoneResponseDto getZone(UUID id) {
        Zone zone = getZoneIfExists(id);

        return zoneMapper.toZoneResponseDto(zone);
    }

    @Transactional
    public ZoneResponseDto updateZone(UUID id, UpdateZoneRequestDto updateZoneRequestDto) {
        Zone zone = getZoneIfExists(id);

        String newZoneName = updateZoneRequestDto.getName();
        Short newZoneFloor = updateZoneRequestDto.getFloor();

        if (newZoneName != null) {
            if (newZoneName.isBlank()) {
                throw new ValidationException("Некорректное название зоны");
            }

            if (!newZoneName.equals(zone.getName())) {
                checkZoneNameIsAvailable(newZoneName);
                zone.setName(newZoneName);
            }
        }
        if (newZoneFloor != null && !newZoneFloor.equals(zone.getFloor())) {
            zone.setFloor(newZoneFloor);
        }

        Zone savedZone = zoneRepository.save(zone);

        return zoneMapper.toZoneResponseDto(savedZone);
    }

    @Transactional
    public void deleteZone(UUID id) {
        Zone zone = getZoneIfExists(id);

        zoneRepository.delete(zone);
    }

    private Zone getZoneIfExists(UUID id) {
        return zoneRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    String.format("Зона с id = %s не найдена", id)
                )
            );
    }

    private void checkZoneNameIsAvailable(String name) {
        if (zoneRepository.existsByName(name)) {
            throw new AlreadyExistsException(
                    String.format("Зона с названием = %s уже существует.", name)
            );
        }
    }

}
