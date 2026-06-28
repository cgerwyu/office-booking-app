package ru.ald.officebooking.workspace.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateWorkspaceDto {

    UUID zoneId;

    @Pattern(
            regexp = "^[A-Z]$",
            message = "rowLabel must be one uppercase letter from A to Z."
    )
    String rowLabel;

    @Min(1)
    Integer deskNumber;

    @Min(1)
    Integer capacity;

}
