package ru.ald.officebooking.workspace.mapper;

import org.springframework.stereotype.Component;
import ru.ald.officebooking.workspace.dto.CreateWorkspaceDto;
import ru.ald.officebooking.workspace.dto.ResponseWorkspaceDto;
import ru.ald.officebooking.workspace.model.Workspace;
import ru.ald.officebooking.zones.model.Zone;

@Component
public class WorkspaceMapper {

    public Workspace mapCreateWorkspaceDtoToEntity(CreateWorkspaceDto dto, Zone zone) {
        Workspace workspace = new Workspace();

        workspace.setName(dto.getName());
        workspace.setZone(zone);
        workspace.setType(dto.getType());
        workspace.setRowLabel(dto.getRowLabel());
        workspace.setDeskNumber(dto.getDeskNumber());
        workspace.setCapacity(dto.getCapacity());

        return workspace;
    }

    public ResponseWorkspaceDto mapEntityToResponseWorkspaceDto(Workspace workspace) {
        ResponseWorkspaceDto dto = new ResponseWorkspaceDto();

        dto.setName(workspace.getName());
        dto.setZoneId(workspace.getZone().getId());
        dto.setType(workspace.getType());
        dto.setRowLabel(workspace.getRowLabel());
        dto.setDeskNumber(workspace.getDeskNumber());
        dto.setCapacity(workspace.getCapacity());

        return dto;
    }

}
