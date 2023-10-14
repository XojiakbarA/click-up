package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uz.pdp.clickup.dto.request.StatusRequest;
import uz.pdp.clickup.dto.view.StatusView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.entity.Status;
import uz.pdp.clickup.enums.StatusType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.StatusMapper;
import uz.pdp.clickup.repository.StatusRepository;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.ListService;
import uz.pdp.clickup.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

    private final String resourceName = Status.class.getSimpleName();

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ListService listService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private StatusMapper statusMapper;

    private Status findByNameAndListId(String name, Long listId) {
        return statusRepository.findByNameAndListId(name, listId).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "name", name)
        );
    }
    private Status save(Status status) {
        return statusRepository.save(status);
    }
    private void checkExistsByNameAndListId(String name, Long listId) {
        if (statusRepository.existsByNameAndListId(name, listId)) {
            throw new ResourceExistsException(resourceName, name, name);
        }
    }
    private void checkExistsByNameAndListId(String name, Long listId, Long id) {
        if (statusRepository.existsByNameAndListIdAndIdNot(name, listId, id)) {
            throw new ResourceExistsException(resourceName, name, name);
        }
    }
    private void setAttributes(Status status, String name, uz.pdp.clickup.entity.List list, Color color, StatusType type, Integer orderIndex) {
        if (name != null && !name.isBlank()) {
            status.setName(name);
        }
        if (list != null) {
            status.setList(list);
        }
        if (color != null) {
            status.setColor(color);
        }
        if (type != null) {
            status.setType(type);
        }
        if (orderIndex != null) {
            status.setOrderIndex(orderIndex);
        }
    }
    private void setAttributes(Status status, String name, Color color) {
        if (name != null && !name.isBlank()) {
            status.setName(name);
        }
        if (color != null) {
            status.setColor(color);
        }
    }
    @Transactional
    private void setAttributes(StatusRequest request, Status status) {
        String name = request.getName();
        uz.pdp.clickup.entity.List list = listService.findById(request.getListId());
        Color color = colorService.findById(request.getColorId());
        StatusType type = StatusType.CUSTOM;

        Status lastStatus = findByNameAndListId(StatusType.CLOSED.name(), list.getId());

        Integer orderIndex = lastStatus.getOrderIndex();

        lastStatus.setOrderIndex(orderIndex + 1);

        save(lastStatus);

        setAttributes(status, name, list, color, type, orderIndex);
    }

    @Override
    public List<StatusView> getAllByListId(Long listId) {
        return statusRepository.findAllByListId(listId).stream()
                    .map(statusMapper::mapToView).toList();
    }

    @Override
    public StatusView create(StatusRequest request) {
        checkExistsByNameAndListId(request.getName(), request.getListId());

        Status status = new Status();

        setAttributes(request, status);

        return statusMapper.mapToView(save(status));
    }

    @Override
    public StatusView updateById(StatusRequest request, Long id) {
        checkExistsByNameAndListId(request.getName(), request.getListId(), id);

        Status status = findById(id);

        String name = request.getName();
        Color color = colorService.findById(request.getColorId());

        setAttributes(status, name, color);

        return statusMapper.mapToView(save(status));
    }

    @Override
    public void deleteById(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        statusRepository.deleteById(id);
    }
    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    
}
