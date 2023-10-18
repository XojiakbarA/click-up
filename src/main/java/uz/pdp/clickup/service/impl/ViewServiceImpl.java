package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.clickup.dto.request.ViewRequest;
import uz.pdp.clickup.dto.view.ViewView;
import uz.pdp.clickup.entity.Icon;
import uz.pdp.clickup.entity.View;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ViewMapper;
import uz.pdp.clickup.repository.ViewRepository;
import uz.pdp.clickup.service.IconService;
import uz.pdp.clickup.service.ViewService;

@Service
public class ViewServiceImpl implements ViewService {

    private final String resourceName = View.class.getSimpleName();

    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private IconService iconService;
    @Autowired
    private ViewMapper viewMapper;

    private View save(View view) {
        return viewRepository.save(view);
    }
    private void checkExistsByName(String name) {
        if (viewRepository.existsByName(name)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkExistsByName(String name, Long id) {
        if (viewRepository.existsByNameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void setAttributes(View view, String name, Icon icon) {
        if (name != null && !name.isBlank()) {
            view.setName(name);
        }
        if (icon != null) {
            view.setIcon(icon);
        }
    }

    @Override
    public View findById(Long id) {
        return viewRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public View findByName(String name) {
        return viewRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "name", name)
        );
    }

    @Override
    public List<ViewView> getAll() {
        return viewRepository.findAll().stream().map(viewMapper::mapToView).toList();
    }

    @Override
    public ViewView getById(Long id) {
        return viewMapper.mapToView(findById(id));
    }

    @Override
    public ViewView create(ViewRequest request) {
        checkExistsByName(request.getName());

        View view = new View();

        setAttributes(view, request.getName(), iconService.findById(request.getIconId()));

        return viewMapper.mapToView(save(view));
    }

    @Override
    public ViewView updateById(ViewRequest request, Long id) {
        checkExistsByName(request.getName(), id);

        View view = findById(id);

        setAttributes(view, request.getName(), iconService.findById(request.getIconId()));

        return viewMapper.mapToView(save(view));
    }

    @Override
    public void deleteById(Long id) {
        View view = findById(id);

        view.getSpaces().forEach(s -> view.removeSpace(s));

        viewRepository.deleteById(id);
    }

    @Override
    public List<ViewView> getAllBySpaceId(Long id) {
        return viewRepository.findAllBySpacesId(id).stream().map(viewMapper::mapToView).toList();
    }

}
