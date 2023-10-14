package uz.pdp.clickup.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.SpaceRequest;
import uz.pdp.clickup.dto.view.SpaceView;
import uz.pdp.clickup.entity.ClickApp;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.Icon;
import uz.pdp.clickup.entity.Space;
import uz.pdp.clickup.entity.SpaceUser;
import uz.pdp.clickup.entity.View;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.entity.WorkspaceUser;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.enums.ViewType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.SpaceMapper;
import uz.pdp.clickup.repository.SpaceRepository;
import uz.pdp.clickup.service.ClickAppService;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.IconService;
import uz.pdp.clickup.service.SpaceService;
import uz.pdp.clickup.service.ViewService;
import uz.pdp.clickup.service.WorkspaceService;
import uz.pdp.clickup.service.WorkspaceUserService;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final String resourceName = Space.class.getSimpleName();

    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private ColorService colorService;
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private IconService iconService;
    @Autowired
    private ClickAppService clickAppService;
    @Autowired
    private ViewService viewService;
    @Autowired
    private WorkspaceUserService workspaceUserService;
    @Autowired
    private SpaceMapper spaceMapper;

    private Space save(Space space) {
        return spaceRepository.save(space);
    }
    private void checkExistsByNameAndWorkspaceId(String name, Long workspaceId) {
        if (spaceRepository.existsByNameAndWorkspaceId(name, workspaceId)) {
            throw new ResourceExistsException(name, "name", name);
        }
    }
    private void checkExistsByNameAndWorkspaceId(String name, Long workspaceId, Long id) {
        if (spaceRepository.existsByNameAndWorkspaceIdAndIdNot(name, workspaceId, id)) {
            throw new ResourceExistsException(name, "name", name);
        }
    }
    private void setAttributes(Space space, String name, Color color, Workspace workspace, Icon icon, AccessType accessType,
                                Set<ClickApp> clickApps, Set<View> views, Set<SpaceUser> persons) {
        if (color != null) {
            space.setColor(color);
        }
        if (workspace != null) {
            space.setWorkspace(workspace);
        }
        if (icon != null) {
            space.setIcon(icon);
        }
        if (clickApps != null && !clickApps.isEmpty()) {
            space.setClickApps(clickApps);
        }
        if (views != null && !views.isEmpty()) {
            space.setViews(views);
        }
        if (persons != null && !persons.isEmpty()) {
            space.setPersons(persons);
        }
        if (name != null && !name.isBlank()) {
            space.setName(name);
        }
        if (accessType != null) {
            space.setAccessType(accessType);
        }
    }
    private void setAttributes(SpaceRequest request, Space space) {
        Color color = colorService.findById(request.getColorId());

        Workspace workspace = workspaceService.findById(request.getWorkspaceId());

        Icon icon = iconService.findById(request.getIconId());

        Set<ClickApp> clickApps = request.getClickAppIds().stream()
                .map(id -> clickAppService.findById(id))
                .collect(Collectors.toSet());

        Set<View> views = request.getClickAppIds().stream()
                .map(id -> viewService.findById(id))
                .collect(Collectors.toSet());

        views.add(viewService.findByName(ViewType.LIST.name()));

        Set<SpaceUser> persons = new HashSet<>();

        if (request.getPersonIds() != null && !request.getPersonIds().isEmpty()) {
            persons = request.getPersonIds().stream().map(id -> {
                WorkspaceUser workspaceUser = workspaceUserService.findById(id);
                return new SpaceUser(space, workspaceUser.getPerson());
            }).collect(Collectors.toSet());
        }

        SpaceUser spaceUser = new SpaceUser(space, workspace.getOwner());
        persons.add(spaceUser);

        AccessType accessType = null;

        if (request.getAccessType() != null) {
            accessType = AccessType.valueOf(request.getAccessType().toUpperCase());

            if (accessType.equals(AccessType.PUBLIC)) {
                persons = workspaceUserService.findAllByWorkspaceId(workspace.getId()).stream()
                        .map(u -> new SpaceUser(space, u.getPerson()))
                        .collect(Collectors.toSet());
            }
        }

        setAttributes(space, request.getName(), color,workspace, icon, accessType, clickApps, views, persons);
    }

    @Override
    public List<SpaceView> getAllSpacesByWorkspaceId(Long workspaceId) {
        return spaceRepository.findAllByWorkspaceId(workspaceId).stream()
                    .map(spaceMapper::mapToView).toList();
    }

    @Override
    public SpaceView getById(Long id) {
        return spaceMapper.mapToView(findById(id));
    }

    @Override
    public SpaceView create(SpaceRequest request) {
        checkExistsByNameAndWorkspaceId(request.getName(), request.getWorkspaceId());

        Space space = new Space();

        setAttributes(request, space);

        return spaceMapper.mapToView(save(space));
    }

    @Override
    public SpaceView updateById(SpaceRequest request, Long spaceId) {
        checkExistsByNameAndWorkspaceId(request.getName(), request.getWorkspaceId(), spaceId);

        Space space = findById(spaceId);

        setAttributes(request, space);

        return spaceMapper.mapToView(save(space));
    }

    @Override
    public void deleteById(Long id) {
        Space space = findById(id);
        
        space.getClickApps().forEach(space::removeClickApp);

        space.getViews().forEach(space::removeView);

        spaceRepository.deleteById(id);
    }

    @Override
    public Space findById(Long id) {
        if (id == null) return null;
        return spaceRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
