package ru.ald.officebooking.workspace.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import ru.ald.officebooking.zones.model.Zone;

import java.util.UUID;

@Entity
@Table(
    name = "workspaces",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_workspace_zone_row_label_desk_number",
            columnNames = {"zone_id", "row_label", "desk_number"}
        )
    },
    indexes = {
        @Index(name = "idx_workspace_zone", columnList = "zone_id")
    }
)
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    Zone zone;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    WorkspaceType type;

    @Column(name = "row_label")
    String rowLabel;

    @Column(name = "desk_number")
    Integer deskNumber;

    @Column(name="capacity", nullable = false)
    Integer capacity;

}
