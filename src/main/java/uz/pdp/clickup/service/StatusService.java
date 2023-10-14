package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.StatusRequest;
import uz.pdp.clickup.dto.view.StatusView;
import uz.pdp.clickup.entity.Status;

public interface StatusService {
    List<StatusView> getAllByListId(Long listId);

    StatusView create(StatusRequest request);

    StatusView updateById(StatusRequest request, Long id);

    void deleteById(Long id);

    Status findById(Long id);
}
