package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.WorkspaceAuthoritiesRequest;
import uz.pdp.clickup.dto.request.WorkspaceRoleRequest;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.entity.WorkspaceRole;
import uz.pdp.clickup.enums.WorkspaceAuthorityType;

import java.util.List;
import java.util.Set;

public interface WorkspaceRoleService {
    List<WorkspaceRoleView> getAllByWorkspaceId(Long workspaceId);

    WorkspaceRoleView create(String name, Workspace workspace, Set<WorkspaceAuthorityType> workspaceAuthorities);

    void deleteById(Long id);

    WorkspaceRole findById(Long id);

    WorkspaceRoleView addWorkspaceAuthorities(WorkspaceAuthoritiesRequest request, Long id);

    WorkspaceRoleView removeWorkspaceAuthorities(WorkspaceAuthoritiesRequest request, Long id);

    List<WorkspaceRole> createInitWorkspaceRoles(Workspace workspace);
}
