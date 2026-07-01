package ru.ald.officebooking.workspace.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import ru.ald.officebooking.workspace.dto.CreateWorkspaceDto;
import ru.ald.officebooking.workspace.dto.UpdateWorkspaceDto;
import ru.ald.officebooking.workspace.model.Workspace;
import ru.ald.officebooking.workspace.dto.ResponseWorkspaceDto;

import java.util.UUID;

public interface WorkspaceService {

    public ResponseWorkspaceDto createWorkspace(CreateWorkspaceDto dto);

    public Page<ResponseWorkspaceDto> getWorkspacesPage(int page, int size);

    public ResponseWorkspaceDto getWorkspaceById(UUID id);

    public ResponseWorkspaceDto updateWorkspace(UUID id, UpdateWorkspaceDto dto);

    public void deleteWorkspaceById(UUID id);

    public Workspace getWorkspaceEntityById(UUID id);

}
