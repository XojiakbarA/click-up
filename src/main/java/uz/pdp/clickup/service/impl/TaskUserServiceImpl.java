package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.view.TaskUserView;
import uz.pdp.clickup.entity.ListUser;
import uz.pdp.clickup.entity.Task;
import uz.pdp.clickup.entity.TaskUser;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.TaskUserMapper;
import uz.pdp.clickup.repository.TaskUserRepository;
import uz.pdp.clickup.service.ListUserService;
import uz.pdp.clickup.service.TaskService;
import uz.pdp.clickup.service.TaskUserService;

@Service
public class TaskUserServiceImpl implements TaskUserService {

    private final String resourceName = TaskUser.class.getSimpleName();

    @Autowired
    private TaskUserRepository taskUserRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskUserMapper taskUserMapper;
    @Autowired
    private ListUserService listUserService;

    private TaskUser save(TaskUser taskUser) {
        return taskUserRepository.save(taskUser);
    }
    private void checkExistsByTaskIdAndPersonId(Long taskId, Long personId) {
        if (taskUserRepository.existsByTaskIdAndPersonId(taskId, personId)) {
            throw new ResourceExistsException(resourceName, "taskId", taskId);
        }
    }

    @Override
    public List<TaskUserView> getAllByTaskId(Long taskId) {
        return taskUserRepository.findAllByTaskId(taskId).stream().map(taskUserMapper::mapToView).toList();
    }

    @Override
    public TaskUserView create(Long id, Long listUserId) {
        checkExistsByTaskIdAndPersonId(id, listUserId);

        Task task = taskService.findById(id);
        ListUser listUser = listUserService.findById(listUserId);

        TaskUser taskUser = new TaskUser(task, listUser.getPerson());

        return taskUserMapper.mapToView(save(taskUser));
    }

    @Override
    public void deleteById(Long id) {
        if (!taskUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        taskUserRepository.deleteById(id);
    }
    @Override
    public TaskUser findById(Long id) {
        return taskUserRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    
}
