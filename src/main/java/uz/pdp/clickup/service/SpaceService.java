package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.SpaceRequest;
import uz.pdp.clickup.dto.view.SpaceView;
import uz.pdp.clickup.entity.Space;

public interface SpaceService {
    List<SpaceView> getAllSpacesByWorkspaceId(Long workspaceId);

    SpaceView getById(Long id);

    SpaceView create(SpaceRequest request);

    SpaceView updateById(SpaceRequest request, Long id);

    void deleteById(Long id);

    Space findById(Long id);
}
