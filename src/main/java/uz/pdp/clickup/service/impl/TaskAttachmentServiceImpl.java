package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.entity.TaskAttachment;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.repository.TaskAttachmentRepository;
import uz.pdp.clickup.service.TaskAttachmentService;

@Service
public class TaskAttachmentServiceImpl implements TaskAttachmentService {

    private final String resourceName = TaskAttachment.class.getSimpleName();

    @Autowired
    private TaskAttachmentRepository taskAttachmentRepository;

    @Override
    public void deleteById(Long id) {
        if (taskAttachmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        taskAttachmentRepository.deleteById(id);
    }
    
}
