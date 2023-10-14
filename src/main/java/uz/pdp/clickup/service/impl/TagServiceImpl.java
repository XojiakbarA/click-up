package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.TagRequest;
import uz.pdp.clickup.dto.view.TagView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.Tag;
import uz.pdp.clickup.entity.Task;
import uz.pdp.clickup.entity.Workspace;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.TagMapper;
import uz.pdp.clickup.repository.TagRepository;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.TagService;
import uz.pdp.clickup.service.TaskService;
import uz.pdp.clickup.service.WorkspaceService;

@Service
public class TagServiceImpl implements TagService {

    private final String resourceName = Tag.class.getSimpleName();

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private TagMapper tagMapper;

    private Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    private Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
    private void checkExistsByNameAndWorkspaceId(String name, Long workspaceId) {
        if (tagRepository.existsByNameAndWorkspaceId(name, workspaceId)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByNameAndWorkspaceId(String name, Long workspaceId, Long id) {
        if (tagRepository.existsByNameAndWorkspaceIdAndIdNot(name, workspaceId, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(Tag tag, String name, Workspace workspace, Color color, Task task) {
        if (name != null && !name.isBlank()) {
            tag.setName(name);
        }
        if (workspace != null) {
            tag.setWorkspace(workspace);
        }
        if (color != null) {
            tag.setColor(color);
        }
        if (task != null) {
            tag.addTask(task);
        }
    }
    private void setAttributes(Tag tag, TagRequest request) {
        String name = request.getName();
        Workspace workspace = workspaceService.findById(request.getWorkspaceId());
        Color color = colorService.findById(request.getColorId());
        Task task = taskService.findById(request.getTaskId());

        setAttributes(tag, name, workspace, color, task);
    }

    @Override
    public List<TagView> getAllByWorkspaceId(Long id) {
        return tagRepository.findAllByWorkspaceId(id).stream().map(tagMapper::mapToView).toList();
    }

    @Override
    public TagView getById(Long id) {
        return tagMapper.mapToView(findById(id));
    }

    @Override
    public TagView create(TagRequest request) {
        checkExistsByNameAndWorkspaceId(request.getName(), request.getWorkspaceId());

        Tag tag = new Tag();

        setAttributes(tag, request);

        return tagMapper.mapToView(save(tag));
    }

    @Override
    public TagView updateById(TagRequest request, Long id) {
        checkExistsByNameAndWorkspaceId(request.getName(), request.getWorkspaceId(), id);

        Tag tag = findById(id);

        setAttributes(tag, request);

        return tagMapper.mapToView(save(tag));
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = findById(id);

        tag.getTasks().forEach(tag::removeTask);

        tagRepository.deleteById(id);
    }
    
}
