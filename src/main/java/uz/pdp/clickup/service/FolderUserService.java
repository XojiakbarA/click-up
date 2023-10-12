package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.view.FolderUserView;

import java.util.List;

public interface FolderUserService {
    List<FolderUserView> getAllByFolderId(Long folderId);

    FolderUserView create(Long id, Long spaceUserId);

    void deleteById(Long id);
}
