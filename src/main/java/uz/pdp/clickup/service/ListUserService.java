package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.view.ListUserView;
import uz.pdp.clickup.entity.ListUser;

public interface ListUserService {
    List<ListUser> findAllByListId(Long listId);

    List<ListUserView> getAllByListId(Long listId);

    ListUserView create(Long id, Long folderUserId);

    void deleteById(Long id);

    ListUser findById(Long id);

}
