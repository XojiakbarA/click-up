package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickup.enums.WorkspaceAuthorityType;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "workspace_roles")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "workspace_id" }) })
public class WorkspaceRole extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    private Set<WorkspaceAuthorityType> workspaceAuthorities = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspaceRole")
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();

    public WorkspaceRole(String name, Workspace workspace, Set<WorkspaceAuthorityType> workspaceAuthorities) {
        this.name = name;
        this.workspace = workspace;
        this.workspaceAuthorities = workspaceAuthorities;
    }
}
