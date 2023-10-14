package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.view.SpaceUserView;
import uz.pdp.clickup.entity.SpaceUser;

import java.util.List;

public interface SpaceUserService {
    List<SpaceUser> findAllBySpaceId(Long spaceId);

    SpaceUser findById(Long id);

    List<SpaceUserView> getAllBySpaceId(Long spaceId);

    SpaceUserView create(Long spaceId, Long workspaceUserId);

    void deleteById(Long id);
}
