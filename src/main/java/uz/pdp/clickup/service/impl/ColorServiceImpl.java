package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.ColorRequest;
import uz.pdp.clickup.dto.view.ColorView;
import uz.pdp.clickup.entity.Color;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ColorMapper;
import uz.pdp.clickup.repository.ColorRepository;
import uz.pdp.clickup.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

    private final String resourceName = Color.class.getSimpleName();

    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ColorMapper colorMapper;

    private Color save(Color color) {
        return colorRepository.save(color);
    }
    private void checkToExistsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkToExistsByName(String name, Long id) {
        if (colorRepository.existsByNameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkToExistsByHexCode(String hexCode) {
        if (colorRepository.existsByHexCode(hexCode)) {
            throw new ResourceExistsException(resourceName, "hexCode", hexCode);
        }
    }
    private void checkToExistsByHexCode(String hexCode, Long id) {
        if (colorRepository.existsByHexCodeAndIdNot(hexCode, id)) {
            throw new ResourceExistsException(resourceName, "hexCode", hexCode);
        }
    }
    private void setAttributes(ColorRequest request, Color color) {
        if (request.getName() != null && !request.getName().isBlank()) {
            color.setName(request.getName());
        }
        if (request.getHexCode() != null && !request.getHexCode().isBlank()) {
            color.setHexCode(request.getHexCode());
        }
    }

    @Override
    public Color findById(Long id) {
        if (id == null) return null;
        return colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public Page<ColorView> getAll(Pageable pageable) {
        return colorRepository.findAll(pageable).map(colorMapper::mapToView);
    }

    @Override
    public ColorView getById(Long id) {
        return colorMapper.mapToView(findById(id));
    }

    @Override
    public ColorView create(ColorRequest request) {
        checkToExistsByName(request.getName());
        checkToExistsByHexCode(request.getHexCode());

        Color color = new Color();

        setAttributes(request, color);

        return colorMapper.mapToView(save(color));
    }

    @Override
    public void create(String name, String hexCode) {
        Color color = new Color();

        color.setName(name.toLowerCase());
        color.setHexCode(hexCode.toLowerCase());

        save(color);
    }

    @Override
    public ColorView update(ColorRequest request, Long id) {
        checkToExistsByName(request.getName(), id);
        checkToExistsByHexCode(request.getHexCode(), id);

        Color color = findById(id);

        setAttributes(request, color);

        return colorMapper.mapToView(save(color));
    }

    @Override
    public void deleteById(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        colorRepository.deleteById(id);
    }
}
