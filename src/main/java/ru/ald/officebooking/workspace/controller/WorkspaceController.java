package ru.ald.officebooking.workspace.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ald.officebooking.workspace.dto.CreateWorkspaceDto;
import ru.ald.officebooking.workspace.dto.ResponseWorkspaceDto;
import ru.ald.officebooking.workspace.dto.UpdateWorkspaceDto;
import ru.ald.officebooking.workspace.service.WorkspaceService;

import static ru.ald.officebooking.common.ApiPaths.ID_PATH;

import java.util.UUID;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<ResponseWorkspaceDto> createWorkspace(@Valid @RequestBody CreateWorkspaceDto dto) {
        ResponseWorkspaceDto responseDto = workspaceService.createWorkspace(dto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseDto);
    }

    @GetMapping
    public Page<ResponseWorkspaceDto> getWorkspacesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return workspaceService.getWorkspacesPage(page, size);
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<ResponseWorkspaceDto> getWorkspaceById(@PathVariable UUID id) {
        ResponseWorkspaceDto responseDto = workspaceService.getWorkspaceById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @PatchMapping(ID_PATH)
    public ResponseEntity<ResponseWorkspaceDto> updateWorkspace(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateWorkspaceDto dto
    ) {
        ResponseWorkspaceDto responseDto = workspaceService.updateWorkspace(id, dto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseDto);
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteWorkspaceById(@PathVariable UUID id) {
        workspaceService.deleteWorkspaceById(id);
        return ResponseEntity.noContent().build();
    }
}
