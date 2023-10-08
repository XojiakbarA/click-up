package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "workspace_users")
public class WorkspaceUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Workspace workspace;

    @ManyToOne(optional = false)
    private User member;

    @ManyToOne(optional = false)
    private WorkspaceRole workspaceRole;

    @Column
    private LocalDateTime dateInvited;

    @Column
    private LocalDateTime dateJoined;
}
