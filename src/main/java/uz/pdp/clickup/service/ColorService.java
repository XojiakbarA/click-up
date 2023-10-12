package uz.pdp.clickup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.clickup.dto.request.ColorRequest;
import uz.pdp.clickup.dto.view.ColorView;
import uz.pdp.clickup.entity.Color;

public interface ColorService {
    Color findById(Long id);

    Page<ColorView> getAll(Pageable pageable);

    ColorView getById(Long id);

    ColorView create(ColorRequest request);

    void create(String name, String hexCode);

    ColorView update(ColorRequest request, Long id);

    void deleteById(Long id);
}
