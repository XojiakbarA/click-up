package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.repository.ColorRepository;
import uz.pdp.clickup.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

    private final String resourceName = Color.class.getSimpleName();

    @Autowired
    private ColorRepository colorRepository;

    private Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void create(String name, String hexCode) {
        Color color = new Color();

        color.setName(name.toLowerCase());
        color.setHexCode(hexCode.toLowerCase());

        save(color);
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
}
