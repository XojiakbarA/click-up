package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.clickup.enums.WorkspaceRoleType;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "workspace_roles")
public class WorkspaceRole extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    private Workspace workspace;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkspaceRoleType extendsRole;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspaceRole")
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspaceRole")
    private Set<WorkspaceAuthority> workspaceAuthorities = new HashSet<>();
}
