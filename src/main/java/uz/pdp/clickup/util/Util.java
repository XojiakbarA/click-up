package uz.pdp.clickup.util;

import java.util.Collection;

import org.springframework.stereotype.Component;

import uz.pdp.clickup.entity.WorkspaceRole;
import uz.pdp.clickup.enums.WorkspaceRoleType;

@Component
public class Util {
    public WorkspaceRole getWorkspaceRoleByType(WorkspaceRoleType workspaceRoleType, Collection<WorkspaceRole> workspaceRoles) {
        return workspaceRoles.stream()
            .filter(r -> r.getName().equals(workspaceRoleType.name()))
            .findFirst().orElse(null);
    }
}
