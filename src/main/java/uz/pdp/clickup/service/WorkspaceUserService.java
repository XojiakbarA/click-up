package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.auth.JoinRequest;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.entity.WorkspaceRole;
import uz.pdp.clickup.entity.WorkspaceUser;

import java.util.List;

public interface WorkspaceUserService {
    List<WorkspaceUserView> getAllMembersByWorkspaceId(Long workspaceId);

    List<WorkspaceUserView> getAllGuestsByWorkspaceId(Long workspaceId);

    WorkspaceUser create(Workspace workspace, WorkspaceRole workspaceRole, User person);

    WorkspaceUserView setWorkspaceRole(Long workspaceUserId, Long workspaceRoleId);

    void join(JoinRequest request);

    void deleteById(Long id);
}
