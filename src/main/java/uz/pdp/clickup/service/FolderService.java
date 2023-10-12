package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.FolderRequest;
import uz.pdp.clickup.dto.view.FolderView;
import uz.pdp.clickup.entity.Folder;

import java.util.List;

public interface FolderService {
    List<FolderView> getAllBySpaceId(Long spaceId);

    FolderView create(FolderRequest request);

    FolderView updateById(FolderRequest request, Long id);

    void deleteById(Long id);

    Folder findById(Long id);
}
