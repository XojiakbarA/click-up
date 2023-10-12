package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.ClickAppRequest;
import uz.pdp.clickup.dto.view.ClickAppView;
import uz.pdp.clickup.entity.ClickApp;
import uz.pdp.clickup.entity.Icon;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ClickAppMapper;
import uz.pdp.clickup.repository.ClickAppRepository;
import uz.pdp.clickup.service.ClickAppService;
import uz.pdp.clickup.service.IconService;

@Service
public class ClickAppServiceImpl implements ClickAppService {

    private final String resourceName = ClickApp.class.getSimpleName();

    @Autowired
    private ClickAppRepository clickAppRepository;
    @Autowired
    private IconService iconService;
    @Autowired
    private ClickAppMapper clickAppMapper;

    private ClickApp save(ClickApp clickApp) {
        return clickAppRepository.save(clickApp);
    }
    private void checkExistsByName(String name) {
        if (clickAppRepository.existsByName(name)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByName(String name, Long id) {
        if (clickAppRepository.existsByNameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(ClickApp clickApp, String name, Icon icon) {
        if (icon != null) {
            clickApp.setIcon(icon);
        }
        if (name != null && !name.isBlank()) {
            clickApp.setName(name);
        }
    }

    @Override
    public ClickApp findById(Long id) {
        return clickAppRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public List<ClickAppView> getAll() {
        return clickAppRepository.findAll().stream()
                        .map(clickAppMapper::mapToView).toList();
    }

    @Override
    public ClickAppView getById(Long id) {
        return clickAppMapper.mapToView(findById(id));
    }

    @Override
    public ClickAppView create(ClickAppRequest request) {
        checkExistsByName(request.getName());

        ClickApp clickApp = new ClickApp();

        setAttributes(clickApp, request.getName(), iconService.findById(request.getIconId()));

        return clickAppMapper.mapToView(save(clickApp));
    }

    @Override
    public ClickAppView updateById(ClickAppRequest request, Long id) {
        checkExistsByName(request.getName(), id);

        ClickApp clickApp = findById(id);

        String name = request.getName();
        Icon icon = iconService.findById(request.getIconId());

        setAttributes(clickApp, name, icon);

        return clickAppMapper.mapToView(save(clickApp));
    }

    @Override
    public void deleteById(Long id) {
        ClickApp clickApp = findById(id);

        clickApp.getSpaces().forEach(s -> clickApp.removeSpace(s));

        clickAppRepository.deleteById(id);
    }
    
}
