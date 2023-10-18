package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.ItemRequest;
import uz.pdp.clickup.dto.view.ItemView;
import uz.pdp.clickup.entity.Item;
import uz.pdp.clickup.entity.TaskUser;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.ItemMapper;
import uz.pdp.clickup.repository.ItemRepository;
import uz.pdp.clickup.service.ChecklistService;
import uz.pdp.clickup.service.ItemService;
import uz.pdp.clickup.service.TaskUserService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final String resourceName = Item.class.getSimpleName();

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ChecklistService checklistService;
    @Autowired
    private TaskUserService taskUserService;
    @Autowired
    private ItemMapper itemMapper;

    private Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    private Item save(Item item) {
        return itemRepository.save(item);
    }
    private void setAttributes(Item item, ItemRequest request) {
        if (request.getChecklistId() != null) {
            item.setChecklist(checklistService.findById(request.getChecklistId()));
        }
        if (request.getName() != null && !request.getName().isBlank()) {
            item.setName(request.getName());
        }
    }

    @Override
    public List<ItemView> getAllByChecklistId(Long checklistId) {
        return itemRepository.findAllByChecklistId(checklistId).stream().map(itemMapper::mapToView).toList();
    }

    @Override
    public ItemView create(ItemRequest request) {
        Item item = new Item();

        setAttributes(item, request);

        return itemMapper.mapToView(save(item));
    }

    @Override
    public ItemView updateById(ItemRequest request, Long id) {
        Item item = findById(id);

        setAttributes(item, request);

        return itemMapper.mapToView(save(item));
    }

    @Override
    public void deleteById(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        itemRepository.deleteById(id);
    }

    @Override
    public ItemView setAssignedUser(Long id, Long taskUserId) {
        Item item = findById(id);
        TaskUser taskUser = taskUserService.findById(taskUserId);

        item.setAssignedUser(taskUser);

        return itemMapper.mapToView(save(item));
    }

    @Override
    public ItemView removeAssignedUser(Long id) {
        Item item = findById(id);

        item.setAssignedUser(null);

        return itemMapper.mapToView(save(item));
    }
}
