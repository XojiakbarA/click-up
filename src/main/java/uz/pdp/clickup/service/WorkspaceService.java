package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.WorkspaceRequest;
import uz.pdp.clickup.dto.request.WorkspaceRoleRequest;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceView> getAllByAuthUserId();

    WorkspaceView getById(Long id);

    WorkspaceView create(WorkspaceRequest request);

    void create(String name, Color color, User owner);

    WorkspaceRoleView createWorkspaceRole(WorkspaceRoleRequest request, Long id);

    WorkspaceView update(WorkspaceRequest request, Long id);

    WorkspaceView setOwnerById(Long id, Long ownerId);

    void invitePersonByEmail(Long id, String email);

    void deleteById(Long id);

    Workspace findById(Long id);
}
