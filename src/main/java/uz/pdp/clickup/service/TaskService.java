package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.TaskRequest;
import uz.pdp.clickup.dto.view.TaskView;
import uz.pdp.clickup.entity.Task;

public interface TaskService {
    List<TaskView> getAllByListId(Long listId);

    TaskView create(TaskRequest request);

    TaskView updateById(TaskRequest request, Long id);

    void deleteById(Long id);
    
    Task findById(Long id);

    TaskView setAssignedUser(Long id, Long taskUserId);

    TaskView removeAssignedUser(Long id);
}
