package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.ClickAppRequest;
import uz.pdp.clickup.dto.view.ClickAppView;
import uz.pdp.clickup.entity.ClickApp;

public interface ClickAppService {
    ClickApp findById(Long id);

    List<ClickAppView> getAll();

    ClickAppView getById(Long id);

    ClickAppView create(ClickAppRequest request);

    ClickAppView updateById(ClickAppRequest request, Long id);

    void deleteById(Long id);

}
