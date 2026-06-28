package ru.ald.officebooking.workspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ald.officebooking.workspace.model.Workspace;
import ru.ald.officebooking.workspace.model.WorkspaceType;

import java.util.Optional;
import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {

    Long countByType(WorkspaceType type);

    Optional<Workspace> getWorkspaceById(UUID id);
}
