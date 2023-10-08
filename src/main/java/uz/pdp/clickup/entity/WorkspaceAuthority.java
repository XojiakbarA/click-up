package uz.pdp.clickup.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "workspace_permissions")
public class WorkspaceAuthority extends BaseEntity {
    @ManyToOne
    private WorkspaceRole workspaceRole;

    @Column(nullable = false)
    private Set<AuthorityType> authorities = new HashSet<>();
}
