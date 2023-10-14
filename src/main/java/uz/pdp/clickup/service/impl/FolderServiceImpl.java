package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.FolderRequest;
import uz.pdp.clickup.dto.view.FolderView;
import uz.pdp.clickup.entity.*;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.enums.TaskAuthorityType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.FolderMapper;
import uz.pdp.clickup.repository.FolderRepository;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.FolderService;
import uz.pdp.clickup.service.SpaceService;
import uz.pdp.clickup.service.SpaceUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    private final String resourceName = Folder.class.getSimpleName();

    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderMapper folderMapper;
    @Autowired
    private SpaceService spaceService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SpaceUserService spaceUserService;

    private Folder save(Folder folder) {
        return folderRepository.save(folder);
    }
    private void checkExistsByName(String name, Long spaceId) {
        if (folderRepository.existsByNameAndSpaceId(name, spaceId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByName(String name, Long spaceId, Long id) {
        if (folderRepository.existsByNameAndSpaceIdAndIdNot(name, spaceId, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(Folder folder, String name, Space space, Color color, AccessType accessType, Set<FolderUser> persons) {
        if (name != null && !name.isBlank()) {
            folder.setName(name);
        }
        if (space != null) {
            folder.setSpace(space);
        }
        if (color != null) {
            folder.setColor(color);
        }
        if (accessType != null) {
            folder.setAccessType(accessType);
        }
        if (persons != null && !persons.isEmpty()) {
            folder.setPersons(persons);
        }
    }
    private void setAttributes(FolderRequest request, Folder folder) {
        String name = request.getName();
        Space space = spaceService.findById(request.getSpaceId());
        Color color = colorService.findById(request.getColorId());

        Set<FolderUser> persons = new HashSet<>();

        if (request.getPersonIds() != null && !request.getPersonIds().isEmpty()) {
            persons = request.getPersonIds().stream().map(id -> {
                SpaceUser spaceUser = spaceUserService.findById(id);
                return new FolderUser(folder, spaceUser.getPerson(), TaskAuthorityType.FULL);
            }).collect(Collectors.toSet());
        }

        FolderUser folderUser = new FolderUser(folder, space.getOwner(), TaskAuthorityType.FULL);
        persons.add(folderUser);

        AccessType accessType = null;

        if (request.getAccessType() != null) {
            accessType = AccessType.valueOf(request.getAccessType().toUpperCase());

            if (accessType.equals(AccessType.PUBLIC)) {
                persons = spaceUserService.findAllBySpaceId(space.getId()).stream()
                        .map(u -> new FolderUser(folder, u.getPerson(), TaskAuthorityType.FULL))
                        .collect(Collectors.toSet());
            }
        }

        setAttributes(folder, name, space, color, accessType, persons);
    }

    @Override
    public List<FolderView> getAllBySpaceId(Long spaceId) {
        return folderRepository.findAllBySpaceId(spaceId).stream()
                .map(folderMapper::mapToView).toList();
    }

    @Override
    public FolderView create(FolderRequest request) {
        checkExistsByName(request.getName(), request.getSpaceId());

        Folder folder = new Folder();

        setAttributes(request, folder);

        return folderMapper.mapToView(save(folder));
    }

    @Override
    public FolderView updateById(FolderRequest request, Long id) {
        checkExistsByName(request.getName(), request.getSpaceId(), id);

        Folder folder = findById(id);

        setAttributes(request, folder);

        return folderMapper.mapToView(save(folder));
    }

    @Override
    public void deleteById(Long id) {
        if (!folderRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        folderRepository.deleteById(id);
    }

    @Override
    public Folder findById(Long id) {
        if (id == null) return null;
        return folderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
