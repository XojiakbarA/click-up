package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.view.TaskUserView;
import uz.pdp.clickup.entity.TaskUser;

public interface TaskUserService {

    List<TaskUserView> getAllByTaskId(Long taskId);

    TaskUserView create(Long id, Long listUserId);

    void deleteById(Long id);

    TaskUser findById(Long id);

}
