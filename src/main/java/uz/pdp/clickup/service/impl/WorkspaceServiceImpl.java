package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import uz.pdp.clickup.util.Util;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private WorkspaceMapper workspaceMapper;
    @Autowired
    private Util util;

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
    private Set<WorkspaceRole> buildInitWorkspaceRoles(Workspace workspace) {
        return Arrays.stream(WorkspaceRoleType.values()).map(type -> {
            Set<WorkspaceAuthorityType> workspaceAuthorities = Arrays.stream(WorkspaceAuthorityType.values()).collect(Collectors.toSet());
            return new WorkspaceRole(type.name(), workspace, workspaceAuthorities);
        }).collect(Collectors.toSet());
    }
    private void setAttributes(Workspace workspace, String name, Color color, User owner, Set<WorkspaceUser> persons, Set<WorkspaceRole> workspaceRoles) {
        if (name != null && !name.isBlank()) {
            workspace.setName(name);
        }
        if (color != null) {
            workspace.setColor(color);
        }
        if (owner != null) {
            workspace.setOwner(owner);
        }
        if (persons != null && !persons.isEmpty()) {
            workspace.setPersons(persons);
        }
        if (workspaceRoles != null && !workspaceRoles.isEmpty()) {
            workspace.setWorkspaceRoles(workspaceRoles);
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
    public List<WorkspaceView> getAllByUserId(Long userId) {
        return workspaceRepository.findAllByOwnerId(userId).stream()
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
        Set<WorkspaceRole> workspaceRoles = buildInitWorkspaceRoles(workspace);
        WorkspaceUser workspaceUser = new WorkspaceUser(workspace, authUser, util.getWorkspaceRoleByType(WorkspaceRoleType.OWNER, workspaceRoles));

        setAttributes(workspace, name, color, authUser, Set.of(workspaceUser), workspaceRoles);

        return workspaceMapper.mapToView(save(workspace));
    }
    @Override
    public void create(String name, Color color, User owner) {
        checkToExistsByName(name, owner.getId());

        Workspace workspace = new Workspace();

        Set<WorkspaceRole> workspaceRoles = buildInitWorkspaceRoles(workspace);
        WorkspaceUser workspaceUser = new WorkspaceUser(workspace, owner, util.getWorkspaceRoleByType(WorkspaceRoleType.OWNER, workspaceRoles));

        setAttributes(workspace, name, color, owner, Set.of(workspaceUser), workspaceRoles);

        save(workspace);
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
    public void deleteById(Long id) {
        if (!workspaceRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        workspaceRepository.deleteById(id);
    }

    @Override
    public Workspace findById(Long id) {
        if (id == null) return null;
        return workspaceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
