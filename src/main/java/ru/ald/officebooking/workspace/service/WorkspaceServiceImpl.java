package ru.ald.officebooking.workspace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ald.officebooking.exception.NotFoundException;
import ru.ald.officebooking.exception.ValidationException;
import ru.ald.officebooking.workspace.dto.CreateWorkspaceDto;
import ru.ald.officebooking.workspace.dto.ResponseWorkspaceDto;
import ru.ald.officebooking.workspace.dto.UpdateWorkspaceDto;
import ru.ald.officebooking.workspace.mapper.WorkspaceMapper;
import ru.ald.officebooking.workspace.model.Workspace;
import ru.ald.officebooking.workspace.model.WorkspaceType;
import ru.ald.officebooking.workspace.repository.WorkspaceRepository;
import ru.ald.officebooking.zones.model.Zone;
import ru.ald.officebooking.zones.service.ZoneService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final ZoneService zoneService;

    @Override
    @Transactional
    public ResponseWorkspaceDto createWorkspace(CreateWorkspaceDto dto) {
        validateWorkspaceFields(
            dto.getType(),
            dto.getRowLabel(),
            dto.getDeskNumber()
        );

        Zone zone = zoneService.getZoneEntityById(dto.getZoneId());

        Workspace workspace = workspaceMapper.mapCreateWorkspaceDtoToEntity(dto, zone);

        String workspaceName = generateWorkspaceName(
            dto.getType(),
            dto.getRowLabel(),
            dto.getDeskNumber()
        );

        workspace.setName(workspaceName);

        Workspace savedWorkspace = workspaceRepository.save(workspace);

        return workspaceMapper.mapEntityToResponseWorkspaceDto(savedWorkspace);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseWorkspaceDto> getWorkspacesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Workspace> workspacesPage = workspaceRepository.findAll(pageable);

        return workspacesPage.map(workspaceMapper::mapEntityToResponseWorkspaceDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseWorkspaceDto getWorkspaceById(UUID id) {
        Workspace workspace = getWorkspaceByIdIfExists(id);

        return workspaceMapper.mapEntityToResponseWorkspaceDto(workspace);
    }

    @Override
    @Transactional
    public ResponseWorkspaceDto updateWorkspace(UUID id, UpdateWorkspaceDto dto) {
        Workspace workspace = getWorkspaceByIdIfExists(id);

        if (dto.getZoneId() != null) {
            Zone newZone = zoneService.getZoneEntityById(dto.getZoneId());
            workspace.setZone(newZone);
        }

        if (dto.getCapacity() != null) {
            workspace.setCapacity(dto.getCapacity());
        }

        if (dto.getRowLabel() != null || dto.getDeskNumber() != null) {
            if (workspace.getType() != WorkspaceType.DESK) {
                throw new ValidationException("Rooms can't have rowLabel or deskNumber.");
            }

            if (dto.getRowLabel() != null) {
                workspace.setRowLabel(dto.getRowLabel());
            }

            if (dto.getDeskNumber() != null) {
                workspace.setDeskNumber(dto.getDeskNumber());
            }

            validateWorkspaceFields(
                workspace.getType(),
                workspace.getRowLabel(),
                workspace.getDeskNumber()
            );

            workspace.setName(
                generateWorkspaceName(
                    workspace.getType(),
                    workspace.getRowLabel(),
                    workspace.getDeskNumber()
                )
            );
        }

        return workspaceMapper.mapEntityToResponseWorkspaceDto(workspace);
    }

    @Override
    @Transactional
    public void deleteWorkspaceById(UUID id) {
        Workspace workspace = getWorkspaceByIdIfExists(id);

        workspaceRepository.delete(workspace);
    }

    private Workspace getWorkspaceByIdIfExists(UUID id) {
        return workspaceRepository.getWorkspaceById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    String.format("Workspace with id = %s not found.", id)
                )
            );
    }

    private void validateWorkspaceFields(
        WorkspaceType type,
        String rowLabel,
        Integer deskNumber
    ) {
        if (type == WorkspaceType.DESK) {
            validateDeskFields(rowLabel, deskNumber);
        } else if (type == WorkspaceType.MEETING_ROOM || type == WorkspaceType.GAME_ROOM) {
            validateRoomFields(type, rowLabel, deskNumber);
        } else {
            throw new ValidationException("Unknown workspace type.");
        }
    }

    private void validateDeskFields(String rowLabel, Integer deskNumber) {
        if (rowLabel == null || rowLabel.isBlank()) {
            throw new ValidationException("rowLabel is required for DESK workspaces.");
        }

        if (!rowLabel.matches("^[A-Z]$")) {
            throw new ValidationException("rowLabel must be one uppercase letter from A to Z.");
        }

        if (deskNumber == null) {
            throw new ValidationException("deskNumber is required for DESK workspaces.");
        }

        if (deskNumber < 1) {
            throw new ValidationException("deskNumber must be greater than or equal to 1.");
        }
    }

    private void validateRoomFields(
        WorkspaceType type,
        String rowLabel,
        Integer deskNumber
    ) {
        if (rowLabel != null) {
            throw new ValidationException(
                String.format("Workspace with type %s shouldn't have rowLabel.", type)
            );
        }

        if (deskNumber != null) {
            throw new ValidationException(
                String.format("Workspace with type %s shouldn't have deskNumber.", type)
            );
        }
    }

    private String generateWorkspaceName(
        WorkspaceType type,
        String rowLabel,
        Integer deskNumber
    ) {
        if (type == WorkspaceType.DESK) {
            return rowLabel + String.format("%02d", deskNumber);
        }

        String roomType = type == WorkspaceType.MEETING_ROOM
            ? "Meeting Room"
            : "Game Room";

        long roomNumber = workspaceRepository.countByType(type) + 1L;

        return roomType + " " + roomNumber;
    }
}