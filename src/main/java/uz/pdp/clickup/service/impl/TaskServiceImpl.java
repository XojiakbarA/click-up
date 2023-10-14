package uz.pdp.clickup.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import uz.pdp.clickup.dto.request.TaskRequest;
import uz.pdp.clickup.dto.view.TaskView;
import uz.pdp.clickup.entity.Attachment;
import uz.pdp.clickup.entity.List;
import uz.pdp.clickup.entity.ListUser;
import uz.pdp.clickup.entity.Priority;
import uz.pdp.clickup.entity.Status;
import uz.pdp.clickup.entity.Task;
import uz.pdp.clickup.entity.TaskAttachment;
import uz.pdp.clickup.entity.TaskUser;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.exception.OperationIsNotPossibleException;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.TaskMapper;
import uz.pdp.clickup.repository.TaskRepository;
import uz.pdp.clickup.service.AttachmentService;
import uz.pdp.clickup.service.ListService;
import uz.pdp.clickup.service.ListUserService;
import uz.pdp.clickup.service.PriorityService;
import uz.pdp.clickup.service.StatusService;
import uz.pdp.clickup.service.TaskService;
import uz.pdp.clickup.service.TaskUserService;

@Service
public class TaskServiceImpl implements TaskService {

    private final String resourceName = Task.class.getSimpleName();

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ListService listService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private ListUserService listUserService;
    @Lazy
    @Autowired
    private TaskUserService taskUserService;
    @Autowired
    private TaskMapper taskMapper;

    private Task save(Task task) {
        return taskRepository.save(task);
    }
    private void checkExistsByNameAndListId(String name, Long listId) {
        if (taskRepository.existsByNameAndListId(name, listId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByNameAndListId(String name, Long listId, Long id) {
        if (taskRepository.existsByNameAndListIdAndIdNot(name, listId, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(Task task, String name, String description, Status status, List list, Priority priority, Task parenTask,
                                LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Set<TaskUser> persons) {
        if (name != null && !name.isBlank()) {
            task.setName(name);
        }
        if (description != null && !description.isBlank()) {
            task.setDescription(description);
        }
        if (status != null) {
            task.setStatus(status);
        }
        if (list != null) {
            task.setList(list);
        }
        if (priority != null) {
            task.setPriority(priority);
        }
        if (parenTask != null) {
            task.setParentTask(parenTask);
        }
        if (startDate != null) {
            task.setStartDate(startDate);
        }
        if (startTime != null) {
            task.setStartTime(startTime);
        }
        if (endDate != null) {
            task.setEndDate(endDate);
        }
        if (endTime != null) {
            task.setEndTime(endTime);
        }
        if (persons != null && !persons.isEmpty()) {
            task.setPersons(persons);
        }
    }
    private void setAttributes(Task task, TaskRequest request) {
        if (request.getParentTaskId() != null && request.getParentTaskId().equals(task.getId())) {
            throw new OperationIsNotPossibleException("A task cannot be its own parent task");
        }
        String name = request.getName();
        String description = request.getDescription();
        Status status = statusService.findById(request.getStatusId());
        List list = listService.findById(request.getListId());
        Priority priority = priorityService.findById(request.getPriorityId());
        Task parentTask = findById(request.getParentTaskId());
        LocalDate startDate = request.getStartDate();
        LocalTime startTime = request.getStartTime();
        LocalDate endDate = request.getEndDate();
        LocalTime endTime = request.getEndTime();

        Set<TaskUser> persons = new HashSet<>();

        if (request.getPersonIds() != null && !request.getPersonIds().isEmpty()) {
            persons = request.getPersonIds().stream().map(id -> {
                ListUser listUser = listUserService.findById(id);
                return new TaskUser(task, listUser.getPerson());
            }).collect(Collectors.toSet());
        }

        TaskUser taskUser = new TaskUser(task, list.getFolder().getSpace().getOwner());
        persons.add(taskUser);

        AccessType accessType = null;

        if (request.getAccessType() != null) {
            accessType = AccessType.valueOf(request.getAccessType().toUpperCase());

            if (accessType.equals(AccessType.PUBLIC)) {
                persons = listUserService.findAllByListId(list.getId()).stream()
                        .map(u -> new TaskUser(task, u.getPerson()))
                        .collect(Collectors.toSet());
            }
        }

        setAttributes(task, name, description, status, list, priority, parentTask, startDate, startTime, endDate, endTime, persons);
    }
    private void setAttachments(Task task, Set<MultipartFile> files) {
        if (files != null && !files.isEmpty()) {
            Set<TaskAttachment> attachments = new HashSet<>();

            int count = 0;
            for (MultipartFile file : files) {
                Attachment attachment = attachmentService.save(file);

                attachments.add(new TaskAttachment(task, attachment, count == 0 ? true : false));

                count++;
            }

            task.setAttachments(attachments);
        }
    }

    @Override
    public java.util.List<TaskView> getAllByListId(Long listId) {
        return taskRepository.findAllByListId(listId).stream()
                    .map(taskMapper::mapToView).toList();
    }

    @Transactional
    @Override
    public TaskView create(TaskRequest request) {
        checkExistsByNameAndListId(request.getName(), request.getListId());

        Task task = new Task();

        setAttributes(task, request);

        setAttachments(task, request.getFiles());

        return taskMapper.mapToView(save(task));
    }

    @Transactional
    @Override
    public TaskView updateById(TaskRequest request, Long id) {
        checkExistsByNameAndListId(request.getName(), request.getListId(), id);

        Task task = findById(id);

        setAttributes(task, request);

        setAttachments(task, request.getFiles());

        return taskMapper.mapToView(save(task));
    }

    @Override
    public void deleteById(Long id) {
        Task task = findById(id);

        task.getTags().forEach(task::removeTag);

        taskRepository.deleteById(id);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    @Override
    public TaskView setAssignedUser(Long id, Long taskUserId) {
        Task task = findById(id);

        task.setAssignedUser(taskUserService.findById(taskUserId));

        return taskMapper.mapToView(save(task));
    }
    @Override
    public TaskView removeAssignedUser(Long id) {
        Task task = findById(id);

        task.setAssignedUser(null);

        return taskMapper.mapToView(save(task));
    }
}
