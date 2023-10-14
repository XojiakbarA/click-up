package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.InvitePersonRequest;
import uz.pdp.clickup.dto.request.auth.JoinRequest;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.entity.*;

import java.util.List;

public interface WorkspaceUserService {
    List<WorkspaceUser> findAllByWorkspaceId(Long workspaceId);

    List<WorkspaceUserView> getAllMembersByWorkspaceId(Long workspaceId);

    List<WorkspaceUserView> getAllGuestsByWorkspaceId(Long workspaceId);

    WorkspaceUser create(Workspace workspace, WorkspaceRole workspaceRole, User person);

    WorkspaceUserView setWorkspaceRole(Long workspaceUserId, Long workspaceRoleId);

    void join(JoinRequest request);

    void deleteById(Long id);

    WorkspaceUser findById(Long id);

    void invitePersonByEmail(InvitePersonRequest request);
}
