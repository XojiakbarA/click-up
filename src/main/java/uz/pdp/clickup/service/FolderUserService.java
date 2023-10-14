package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.view.FolderUserView;
import uz.pdp.clickup.entity.FolderUser;

import java.util.List;

public interface FolderUserService {
    List<FolderUser> findAllByFolderId(Long folderId);

    List<FolderUserView> getAllByFolderId(Long folderId);

    FolderUserView create(Long id, Long spaceUserId);

    void deleteById(Long id);

    FolderUser findById(Long id);
}
