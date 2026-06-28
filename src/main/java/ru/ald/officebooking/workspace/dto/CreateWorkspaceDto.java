package ru.ald.officebooking.workspace.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.ald.officebooking.workspace.model.WorkspaceType;

import java.util.UUID;

@Getter
@Setter
public class CreateWorkspaceDto {

    @NotNull
    UUID zoneId;

    @NotNull
    WorkspaceType type;

    String rowLabel;

    Integer deskNumber;

    @NotNull
    @Min(1)
    Integer capacity;

}
