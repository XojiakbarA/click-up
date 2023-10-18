package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.ItemRequest;
import uz.pdp.clickup.dto.view.ItemView;

import java.util.List;

public interface ItemService {
    List<ItemView> getAllByChecklistId(Long checklistId);

    ItemView create(ItemRequest request);

    ItemView updateById(ItemRequest request, Long id);

    void deleteById(Long id);

    ItemView setAssignedUser(Long id, Long taskUserId);

    ItemView removeAssignedUser(Long id);
}
