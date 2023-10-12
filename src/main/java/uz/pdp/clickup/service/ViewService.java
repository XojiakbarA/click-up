package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.ViewRequest;
import uz.pdp.clickup.dto.view.ViewView;
import uz.pdp.clickup.entity.View;

public interface ViewService {
    View findById(Long id);

    View findByName(String name);

    List<ViewView> getAll();

    ViewView getById(Long id);

    ViewView create(ViewRequest request);

    ViewView updateById(ViewRequest request, Long id);

    void deleteById(Long id);
}
