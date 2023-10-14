package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.view.FolderUserView;
import uz.pdp.clickup.entity.Folder;
import uz.pdp.clickup.entity.FolderUser;
import uz.pdp.clickup.entity.SpaceUser;
import uz.pdp.clickup.enums.TaskAuthorityType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.FolderUserMapper;
import uz.pdp.clickup.repository.FolderUserRepository;
import uz.pdp.clickup.service.FolderService;
import uz.pdp.clickup.service.FolderUserService;
import uz.pdp.clickup.service.SpaceUserService;

import java.util.List;

@Service
public class FolderUserServiceImpl implements FolderUserService {

    private final String resourceName = FolderUser.class.getSimpleName();

    @Autowired
    private FolderUserRepository folderUserRepository;
    @Autowired
    private FolderService folderService;
    @Autowired
    private FolderUserMapper folderUserMapper;
    @Autowired
    private SpaceUserService spaceUserService;

    private FolderUser save(FolderUser folderUser) {
        return folderUserRepository.save(folderUser);
    }
    private void checkExistsByFolderIdAndPersonId(Long folderId, Long personId) {
        if (folderUserRepository.existsByFolderIdAndPersonId(folderId, personId)) {
            throw new ResourceExistsException(resourceName, "folderId", folderId);
        }
    }

    @Override
    public List<FolderUserView> getAllByFolderId(Long folderId) {
        return folderUserRepository.findAllByFolderId(folderId).stream()
                .map(folderUserMapper::mapToView).toList();
    }

    @Override
    public FolderUserView create(Long id, Long spaceUserId) {
        checkExistsByFolderIdAndPersonId(id, spaceUserId);

        Folder folder = folderService.findById(id);
        SpaceUser spaceUser = spaceUserService.findById(spaceUserId);

        FolderUser folderUser = new FolderUser(folder, spaceUser.getPerson(), TaskAuthorityType.FULL);

        return folderUserMapper.mapToView(save(folderUser));
    }

    @Override
    public void deleteById(Long id) {
        if (!folderUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        folderUserRepository.deleteById(id);
    }
    
    @Override
    public List<FolderUser> findAllByFolderId(Long folderId) {
        return folderUserRepository.findAllByFolderId(folderId);
    }

    @Override
    public FolderUser findById(Long id) {
        if (id == null) return null;
        return folderUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
