package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.ChecklistRequest;
import uz.pdp.clickup.dto.view.ChecklistView;
import uz.pdp.clickup.entity.Checklist;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ChecklistMapper;
import uz.pdp.clickup.repository.ChecklistRepository;
import uz.pdp.clickup.service.ChecklistService;
import uz.pdp.clickup.service.TaskService;

import java.util.List;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    private final String resourceName = Checklist.class.getSimpleName();

    @Autowired
    private ChecklistRepository checklistRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ChecklistMapper checklistMapper;

    private Checklist save(Checklist checklist) {
        return checklistRepository.save(checklist);
    }
    private void setAttributes(Checklist checklist, ChecklistRequest request) {
        if (request.getTaskId() != null) {
            checklist.setTask(taskService.findById(request.getTaskId()));
        }
        if (request.getName() != null && !request.getName().isBlank()) {
            checklist.setName(request.getName());
        }
    }

    @Override
    public List<ChecklistView> getAllByTaskId(Long taskId) {
        return checklistRepository.findAllByTaskId(taskId).stream().map(checklistMapper::mapToView).toList();
    }

    @Override
    public ChecklistView create(ChecklistRequest request) {
        Checklist checklist = new Checklist();

        setAttributes(checklist, request);

        return checklistMapper.mapToView(save(checklist));
    }

    @Override
    public ChecklistView updateById(ChecklistRequest request, Long id) {
        Checklist checklist = findById(id);

        setAttributes(checklist, request);

        return checklistMapper.mapToView(save(checklist));
    }

    @Override
    public void deleteById(Long id) {
        if (!checklistRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        checklistRepository.deleteById(id);
    }

    @Override
    public Checklist findById(Long id) {
        return checklistRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
