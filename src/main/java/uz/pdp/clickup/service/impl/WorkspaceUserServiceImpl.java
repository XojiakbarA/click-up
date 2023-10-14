package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.InvitePersonRequest;
import uz.pdp.clickup.dto.request.auth.JoinRequest;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.entity.WorkspaceRole;
import uz.pdp.clickup.entity.WorkspaceUser;
import uz.pdp.clickup.enums.WorkspaceRoleType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.WorkspaceUserMapper;
import uz.pdp.clickup.repository.WorkspaceUserRepository;
import uz.pdp.clickup.service.AuthService;
import uz.pdp.clickup.service.MessageService;
import uz.pdp.clickup.service.UserService;
import uz.pdp.clickup.service.WorkspaceRoleService;
import uz.pdp.clickup.service.WorkspaceService;
import uz.pdp.clickup.service.WorkspaceUserService;
import uz.pdp.clickup.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class WorkspaceUserServiceImpl implements WorkspaceUserService {

    private final String resourceName = WorkspaceUser.class.getSimpleName();

    @Autowired
    private WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkspaceRoleService workspaceRoleService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AuthService authService;
    @Autowired
    private WorkspaceUserMapper workspaceUserMapper;
    @Autowired
    private Util util;

    private WorkspaceUser save(WorkspaceUser workspaceUser) {
        return workspaceUserRepository.save(workspaceUser);
    }
    private void checkToExistsByWorkspaceIdAndPersonId(Long workspaceId, Long personId) {
        if (workspaceUserRepository.existsByWorkspaceIdAndPersonId(workspaceId, personId)) {
            throw new ResourceExistsException(resourceName, "workspaceId", workspaceId);
        }
    }
    private void setAttributes(WorkspaceUser workspaceUser, Workspace workspace, WorkspaceRole workspaceRole, User person) {
        if (workspace != null) {
            workspaceUser.setWorkspace(workspace);
        }
        if (workspaceRole != null) {
            workspaceUser.setWorkspaceRole(workspaceRole);
        }
        if (person != null) {
            workspaceUser.setPerson(person);
        }
        Random random = new Random();
        String joinCode = String.format("%04d", random.nextInt(10000));

        workspaceUser.setJoinCode(joinCode);
    }

    @Override
    public List<WorkspaceUserView> getAllMembersByWorkspaceId(Long workspaceId) {
        return workspaceUserRepository.findAllByWorkspaceRoleNameAndWorkspaceId(
                WorkspaceRoleType.MEMBER.name(), workspaceId)
                .stream().map(workspaceUserMapper::mapToView).toList();
    }

    @Override
    public List<WorkspaceUserView> getAllGuestsByWorkspaceId(Long workspaceId) {
        return workspaceUserRepository.findAllByWorkspaceRoleNameAndWorkspaceId(
                        WorkspaceRoleType.GUEST.name(), workspaceId)
                .stream().map(workspaceUserMapper::mapToView).toList();
    }

    @Override
    public WorkspaceUser create(Workspace workspace, WorkspaceRole workspaceRole, User person) {
        checkToExistsByWorkspaceIdAndPersonId(workspace.getId(), person.getId());

        WorkspaceUser workspaceUser = new WorkspaceUser();

        setAttributes(workspaceUser, workspace, workspaceRole, person);

        return save(workspaceUser);
    }

    @Override
    public WorkspaceUserView setWorkspaceRole(Long workspaceUserId, Long workspaceRoleId) {
        WorkspaceUser workspaceUser = findById(workspaceUserId);

        workspaceUser.setWorkspaceRole(workspaceRoleService.findById(workspaceRoleId));

        return workspaceUserMapper.mapToView(save(workspaceUser));
    }

    @Override
    public void join(JoinRequest request) {
        WorkspaceUser workspaceUser = findById(request.getWorkspaceUserId());

        boolean areCodesEqual = workspaceUser.getJoinCode().equals(request.getJoinCode());
        boolean areEmailsEqual = workspaceUser.getPerson().getEmail().equals(request.getEmail());

        if (!areCodesEqual || !areEmailsEqual) {
            throw  new MailAuthenticationException("Join failed");
        }

        workspaceUser.setJoinCode(null);
        workspaceUser.setJoinedAt(LocalDateTime.now());

        save(workspaceUser);
    }

    @Override
    public void deleteById(Long id) {
        if (!workspaceUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        workspaceUserRepository.deleteById(id);
    }

    @Override
    public WorkspaceUser findById(Long id) {
        return workspaceUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    @Override
    public void invitePersonByEmail(InvitePersonRequest request) {
        Workspace workspace = workspaceService.findById(request.getWorkspaceId());
        User person = userService.findByEmail(request.getEmail());
        WorkspaceRole memberRole = util.getWorkspaceRoleByType(WorkspaceRoleType.MEMBER, workspace.getWorkspaceRoles());

        WorkspaceUser workspaceUser = create(workspace, memberRole, person);

        messageService.sendInviteMessage(request.getEmail(), workspaceUser, authService.getAuthUser().getFullName());
    }
    @Override
    public List<WorkspaceUser> findAllByWorkspaceId(Long workspaceId) {
        return workspaceUserRepository.findAllByWorkspaceId(workspaceId);
    }
}
