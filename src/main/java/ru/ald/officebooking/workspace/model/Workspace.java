package ru.ald.officebooking.workspace.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

@Data
@Entity
@Table(name="workspaces", uniqueConstraints = {
        @UniqueConstraint(
                name="uq_workspace_zone_row_label_desk_number",
                columnNames = {"zone_id", "row_label", "desk_number"}
        )
})
public class Workspace {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;


}
