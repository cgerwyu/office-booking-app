package ru.ald.officebooking.workspace.dto;

import lombok.Getter;
import lombok.Setter;
import ru.ald.officebooking.workspace.model.WorkspaceType;

import java.util.UUID;

@Getter
@Setter
public class ResponseWorkspaceDto {

    UUID id;

    String name;

    UUID zoneId;

    WorkspaceType type;

    String rowLabel;

    Integer deskNumber;

    Integer capacity;

}
