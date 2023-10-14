package uz.pdp.clickup.service.impl;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.entity.Priority;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.repository.PriorityRepository;
import uz.pdp.clickup.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final String resourceName = Priority.class.getSimpleName();

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public Priority findById(Long id) {
        return priorityRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    
}
