package uz.pdp.clickup.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.ListRequest;
import uz.pdp.clickup.dto.view.ListView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.Folder;
import uz.pdp.clickup.entity.FolderUser;
import uz.pdp.clickup.entity.List;
import uz.pdp.clickup.entity.ListUser;
import uz.pdp.clickup.entity.Status;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.enums.StatusType;
import uz.pdp.clickup.enums.TaskAuthorityType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ListMapper;
import uz.pdp.clickup.repository.ListRepository;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.FolderService;
import uz.pdp.clickup.service.FolderUserService;
import uz.pdp.clickup.service.ListService;

@Service
public class ListServiceImpl implements ListService {

    private final String resourceName = List.class.getSimpleName();

    @Autowired
    private ListRepository listRepository;
    @Autowired
    private FolderService folderService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private FolderUserService folderUserService;
    @Autowired
    private ListMapper listMapper;

    private List save(List list) {
        return listRepository.save(list);
    }
    private void checkExistsByName(String name, Long folderId) {
        if (listRepository.existsByNameAndFolderId(name, folderId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByName(String name, Long folderId, Long id) {
        if (listRepository.existsByNameAndFolderIdAndIdNot(name, folderId, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(List list, String name, Folder folder, Color color, AccessType accessType, Set<ListUser> persons) {
        if (name != null && !name.isBlank()) {
            list.setName(name);
        }
        if (folder != null) {
            list.setFolder(folder);
        }
        if (color != null) {
            list.setColor(color);
        }
        if (accessType != null) {
            list.setAccessType(accessType);
        }
        if (persons != null && !persons.isEmpty()) {
            list.setPersons(persons);
        }
    }
    private void setAttributes(ListRequest request, List list) {
        String name = request.getName();
        Folder folder = folderService.findById(request.getFolderId());
        Color color = colorService.findById(request.getColorId());

        Set<ListUser> persons = new HashSet<>();

        if (request.getPersonIds() != null && !request.getPersonIds().isEmpty()) {
            persons = request.getPersonIds().stream().map(id -> {
                FolderUser folderUser = folderUserService.findById(id);
                return new ListUser(list, folderUser.getPerson(), TaskAuthorityType.FULL);
            }).collect(Collectors.toSet());
        }

        ListUser listUser = new ListUser(list, folder.getSpace().getOwner(), TaskAuthorityType.FULL);
        persons.add(listUser);

        AccessType accessType = null;

        if (request.getAccessType() != null) {
            accessType = AccessType.valueOf(request.getAccessType().toUpperCase());

            if (accessType.equals(AccessType.PUBLIC)) {
                persons = folderUserService.findAllByFolderId(folder.getId()).stream()
                        .map(u -> new ListUser(list, u.getPerson(), TaskAuthorityType.FULL))
                        .collect(Collectors.toSet());
            }
        }

        setAttributes(list, name, folder, color, accessType, persons);
    }

    @Override
    public java.util.List<ListView> getAllByFolderId(Long id) {
        return listRepository.findAllByFolderId(id).stream()
                    .map(listMapper::mapToView).toList();
    }

    @Override
    public ListView create(ListRequest request) {
        checkExistsByName(request.getName(), request.getFolderId());

        List list = new List();

        setAttributes(request, list);

        Set<Status> statuses = new HashSet<>();
        statuses.add(new Status("TO DO", list, StatusType.NEW, 1));
        statuses.add(new Status("COMPLETED", list, StatusType.CLOSED, 2));

        list.setStatuses(statuses);

        return listMapper.mapToView(save(list));
    }

    @Override
    public ListView updateById(ListRequest request, Long id) {
        checkExistsByName(request.getName(), request.getFolderId(), id);

        List list = findById(id);

        setAttributes(request, list);

        return listMapper.mapToView(save(list));
    }

    @Override
    public void deleteById(Long id) {
        if (!listRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        listRepository.deleteById(id);
    }
    @Override
    public List findById(Long id) {
        if (id == null) return null;
        return listRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    
}
