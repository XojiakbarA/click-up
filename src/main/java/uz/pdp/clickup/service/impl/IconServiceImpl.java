package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.entity.Icon;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.repository.IconRepository;
import uz.pdp.clickup.service.IconService;

@Service
public class IconServiceImpl implements IconService {

    private final String resourceName = Icon.class.getSimpleName();

    @Autowired
    private IconRepository iconRepository;

    @Override
    public Icon findById(Long id) {
        return iconRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    
}
