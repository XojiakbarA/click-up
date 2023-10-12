package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.WorkspaceRequest;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceView> getAllByUserId(Long userId);

    WorkspaceView getById(Long id);

    WorkspaceView create(WorkspaceRequest request);

    void create(String name, Color color, User owner);

    WorkspaceView update(WorkspaceRequest request, Long id);

    WorkspaceView setOwnerById(Long id, Long ownerId);

    void deleteById(Long id);

    Workspace findById(Long id);
}
