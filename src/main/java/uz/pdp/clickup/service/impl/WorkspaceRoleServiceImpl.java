package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.WorkspaceAuthoritiesRequest;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.entity.WorkspaceRole;
import uz.pdp.clickup.enums.WorkspaceAuthorityType;
import uz.pdp.clickup.enums.WorkspaceRoleType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.WorkspaceRoleMapper;
import uz.pdp.clickup.repository.WorkspaceRoleRepository;
import uz.pdp.clickup.service.WorkspaceRoleService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkspaceRoleServiceImpl implements WorkspaceRoleService {

    private final String resourceName = WorkspaceRole.class.getSimpleName();

    @Autowired
    private WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    private WorkspaceRoleMapper workspaceRoleMapper;

    private WorkspaceRole save(WorkspaceRole workspaceRole) {
        return workspaceRoleRepository.save(workspaceRole);
    }
    private List<WorkspaceRole> saveAll(Iterable<WorkspaceRole> workspaceRoles) {
        return workspaceRoleRepository.saveAll(workspaceRoles);
    }
    private void checkToExistsByName(String name, Long workspaceId) {
        if (workspaceRoleRepository.existsByNameAndWorkspaceId(name, workspaceId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }

    private Set<WorkspaceAuthorityType> mapStringsToWorkspaceAuthorities(Set<String> strings) {
        return strings.stream().map(a -> WorkspaceAuthorityType.valueOf(a.toUpperCase())).collect(Collectors.toSet());
    }
    private void setAttributes(WorkspaceRole workspaceRole, String name, Workspace workspace, Set<WorkspaceAuthorityType> workspaceAuthorities) {
        if (name != null && !name.isBlank()) {
            workspaceRole.setName(name);
        }
        if (workspace != null) {
            workspaceRole.setWorkspace(workspace);
        }
        if (workspaceAuthorities != null && !workspaceAuthorities.isEmpty()) {
            workspaceRole.setWorkspaceAuthorities(workspaceAuthorities);
        }
    }

    @Override
    public List<WorkspaceRoleView> getAllByWorkspaceId(Long workspaceId) {
        return workspaceRoleRepository.findAllByWorkspaceId(workspaceId).stream()
                .map(workspaceRoleMapper::mapToView).toList();
    }

    @Override
    public WorkspaceRoleView create(String name, Workspace workspace, Set<WorkspaceAuthorityType> workspaceAuthorities) {
        checkToExistsByName(name, workspace.getId());

        WorkspaceRole workspaceRole = new WorkspaceRole();

        setAttributes(workspaceRole, name, workspace, workspaceAuthorities);

        return workspaceRoleMapper.mapToView(save(workspaceRole));
    }

    @Override
    public void deleteById(Long id) {
        if (!workspaceRoleRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        workspaceRoleRepository.deleteById(id);
    }

    @Override
    public WorkspaceRole findById(Long id) {
        return workspaceRoleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public WorkspaceRoleView addWorkspaceAuthorities(WorkspaceAuthoritiesRequest request, Long id) {
        WorkspaceRole workspaceRole = findById(id);

        Set<WorkspaceAuthorityType> workspaceAuthorities = mapStringsToWorkspaceAuthorities(request.getWorkspaceAuthorities());

        workspaceRole.getWorkspaceAuthorities().addAll(workspaceAuthorities);

        return workspaceRoleMapper.mapToView(workspaceRole);
    }

    @Override
    public WorkspaceRoleView removeWorkspaceAuthorities(WorkspaceAuthoritiesRequest request, Long id) {
        WorkspaceRole workspaceRole = findById(id);

        Set<WorkspaceAuthorityType> workspaceAuthorities = mapStringsToWorkspaceAuthorities(request.getWorkspaceAuthorities());

        workspaceRole.getWorkspaceAuthorities().removeAll(workspaceAuthorities);

        return workspaceRoleMapper.mapToView(workspaceRole);
    }

    @Override
    public List<WorkspaceRole> createInitWorkspaceRoles(Workspace workspace) {
        Set<WorkspaceAuthorityType> workspaceAuthorities = Arrays.stream(WorkspaceAuthorityType.values()).collect(Collectors.toSet());

        Set<WorkspaceRole> workspaceRoles = Arrays.stream(WorkspaceRoleType.values())
                .map(type -> new WorkspaceRole(type.name(), workspace, workspaceAuthorities))
                .collect(Collectors.toSet());

        return saveAll(workspaceRoles);
    }
}
