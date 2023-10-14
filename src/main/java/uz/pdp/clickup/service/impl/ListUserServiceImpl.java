package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.view.ListUserView;
import uz.pdp.clickup.entity.FolderUser;
import uz.pdp.clickup.entity.ListUser;
import uz.pdp.clickup.enums.TaskAuthorityType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ListUserMapper;
import uz.pdp.clickup.repository.ListUserRepository;
import uz.pdp.clickup.service.FolderUserService;
import uz.pdp.clickup.service.ListService;
import uz.pdp.clickup.service.ListUserService;

@Service
public class ListUserServiceImpl implements ListUserService {

    private final String resourceName = ListUser.class.getSimpleName();

    @Autowired
    private ListUserRepository listUserRepository;
    @Autowired
    private ListService listService;
    @Autowired
    private ListUserMapper listUserMapper;
    @Autowired
    private FolderUserService folderUserService;

    private ListUser save(ListUser listUser) {
        return listUserRepository.save(listUser);
    }
    private void checkExistsByListIdAndPersonId(Long listId, Long personId) {
        if (listUserRepository.existsByListIdAndPersonId(listId, personId)) {
            throw new ResourceExistsException(resourceName, "listId", listId);
        }
    }

    @Override
    public List<ListUserView> getAllByListId(Long listId) {
        return findAllByListId(listId).stream().map(listUserMapper::mapToView).toList();
    }

    @Override
    public ListUserView create(Long id, Long folderUserId) {
        checkExistsByListIdAndPersonId(id, folderUserId);

        uz.pdp.clickup.entity.List list = listService.findById(id);
        FolderUser folderUser = folderUserService.findById(folderUserId);

        ListUser listUser = new ListUser(list, folderUser.getPerson(), TaskAuthorityType.FULL);

        return listUserMapper.mapToView(save(listUser));
    }

    @Override
    public void deleteById(Long id) {
        if (!listUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        listUserRepository.deleteById(id);
    }

    @Override
    public ListUser findById(Long id) {
        return listUserRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public List<ListUser> findAllByListId(Long listId) {
        return listUserRepository.findAllByListId(listId);
    }
    
}
