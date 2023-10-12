package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.WorkspaceRoleRequest;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.entity.*;
import uz.pdp.clickup.enums.WorkspaceAuthorityType;
import uz.pdp.clickup.enums.WorkspaceRoleType;
import uz.pdp.clickup.mapper.WorkspaceMapper;
import uz.pdp.clickup.dto.request.WorkspaceRequest;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.repository.WorkspaceRepository;
import uz.pdp.clickup.service.*;

import java.util.*;
import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private final String resourceName = Workspace.class.getSimpleName();

    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private WorkspaceRoleService workspaceRoleService;
    @Autowired
    private WorkspaceUserService workspaceUserService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private WorkspaceMapper workspaceMapper;

    private Workspace save(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }
    private void checkToExistsByName(String name, Long ownerId) {
        if (workspaceRepository.existsByNameAndOwnerId(name, ownerId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkToExistsByName(String name, Long ownerId, Long id) {
        if (workspaceRepository.existsByNameAndOwnerIdAndIdNot(name, ownerId, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private WorkspaceRole getWorkspaceRole(WorkspaceRoleType workspaceRoleType, Collection<WorkspaceRole> workspaceRoles) {
        return workspaceRoles.stream()
                .filter(r -> r.getName().equals(workspaceRoleType.name()))
                .findFirst().orElse(null);
    }
    private void setAttributes(Workspace workspace, String name, Color color, User owner) {
        if (name != null && !name.isBlank()) {
            workspace.setName(name);
        }
        if (color != null) {
            workspace.setColor(color);
        }
        if (owner != null) {
            workspace.setOwner(owner);
        }
    }
    private void setAttributes(Workspace workspace, String name, Color color) {
        if (name != null && !name.isBlank()) {
            workspace.setName(name);
        }
        if (color != null) {
            workspace.setColor(color);
        }
    }

    @Override
    public List<WorkspaceView> getAllByAuthUserId() {
        User authUser = authService.getAuthUser();
        return workspaceRepository.findAllByOwnerId(authUser.getId()).stream()
                .map(workspaceMapper::mapToView).toList();
    }

    @Override
    public WorkspaceView getById(Long id) {
        return workspaceMapper.mapToView(findById(id));
    }

    @Override
    public WorkspaceView create(WorkspaceRequest request) {
        User authUser = authService.getAuthUser();

        checkToExistsByName(request.getName(), authUser.getId());

        Workspace workspace = new Workspace();

        String name = request.getName();
        Color color = colorService.findById(request.getColorId());

        setAttributes(workspace, name, color, authUser);

        List<WorkspaceRole> workspaceRoles = workspaceRoleService.createInitWorkspaceRoles(workspace);

        WorkspaceRole ownerRole = getWorkspaceRole(WorkspaceRoleType.OWNER, workspaceRoles);

        workspaceUserService.create(workspace, ownerRole, authUser);

        return workspaceMapper.mapToView(save(workspace));
    }
    @Override
    public void create(String name, Color color, User owner) {
        checkToExistsByName(name, owner.getId());

        Workspace workspace = new Workspace();

        setAttributes(workspace, name, color, owner);

        List<WorkspaceRole> workspaceRoles = workspaceRoleService.createInitWorkspaceRoles(workspace);

        WorkspaceRole ownerRole = getWorkspaceRole(WorkspaceRoleType.OWNER, workspaceRoles);

        workspaceUserService.create(workspace, ownerRole, owner);

        save(workspace);
    }

    @Override
    public WorkspaceRoleView createWorkspaceRole(WorkspaceRoleRequest request, Long id) {
        Workspace workspace = findById(id);
        String name = request.getName();
        Set<WorkspaceAuthorityType> workspaceAuthorities = null;

        if (request.getExtendsWorkspaceRoleId() != null) {
            WorkspaceRole workspaceRole = workspaceRoleService.findById(request.getExtendsWorkspaceRoleId());
            workspaceAuthorities = workspaceRole.getWorkspaceAuthorities();
        }

        return workspaceRoleService.create(name, workspace, workspaceAuthorities);
    }

    @Override
    public WorkspaceView update(WorkspaceRequest request, Long id) {
        checkToExistsByName(request.getName(), authService.getAuthUser().getId(), id);

        Workspace workspace = findById(id);

        String name = request.getName();
        Color color = colorService.findById(request.getColorId());

        setAttributes(workspace, name, color);

        return workspaceMapper.mapToView(save(workspace));
    }

    @Override
    public WorkspaceView setOwnerById(Long id, Long ownerId) {
        Workspace workspace = findById(id);

        workspace.setOwner(userService.findById(ownerId));

        return workspaceMapper.mapToView(save(workspace));
    }

    @Override
    public void invitePersonByEmail(Long id, String email) {
        Workspace workspace = findById(id);
        User person = userService.findByEmail(email);
        WorkspaceRole memberRole = getWorkspaceRole(WorkspaceRoleType.MEMBER, workspace.getWorkspaceRoles());

        WorkspaceUser workspaceUser = workspaceUserService.create(workspace, memberRole, person);

        messageService.sendInviteMessage(email, workspaceUser, authService.getAuthUser().getFullName());
    }

    @Override
    public void deleteById(Long id) {
        if (!workspaceRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        workspaceRepository.deleteById(id);
    }

    @Override
    public Workspace findById(Long id) {
        return workspaceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
