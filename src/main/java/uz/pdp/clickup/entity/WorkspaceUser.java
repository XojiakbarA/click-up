package uz.pdp.clickup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "workspace_users")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "workspace_id", "person_id" }) })
public class WorkspaceUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Workspace workspace;

    @ManyToOne(optional = false)
    private User person;

    @Column
    private String joinCode;

    @ManyToOne(optional = false)
    private WorkspaceRole workspaceRole;

    @Column(nullable = false)
    private LocalDateTime invitedAt = LocalDateTime.now();

    @Column
    private LocalDateTime joinedAt;

    public WorkspaceUser(Workspace workspace, User person, WorkspaceRole workspaceRole) {
        this.workspace = workspace;
        this.person = person;
        this.workspaceRole = workspaceRole;
    }
}
