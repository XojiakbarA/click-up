package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.ItemRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ItemView;
import uz.pdp.clickup.dto.view.TaskView;
import uz.pdp.clickup.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByTaskId(@PathVariable Long id) {
        List<ItemView> items = itemService.getAllByChecklistId(id);

        return new Response(HttpStatus.OK.name(), items);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ItemRequest request) {
        ItemView item = itemService.create(request);

        return new Response(HttpStatus.CREATED.name(), item);
    }

    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody ItemRequest request, @PathVariable Long id) {
        ItemView item = itemService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), item);
    }

    @PutMapping("/items/{id}/assign-user/{taskUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setAssignedUser(@PathVariable Long id, @PathVariable Long taskUserId) {
        ItemView item = itemService.setAssignedUser(id, taskUserId);

        return new Response(HttpStatus.OK.name(), item);
    }

    @DeleteMapping("/items/{id}/assign-user")
    @ResponseStatus(HttpStatus.OK)
    public Response removeAssignedUser(@PathVariable Long id) {
        ItemView item = itemService.removeAssignedUser(id);

        return new Response(HttpStatus.OK.name(), item);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        itemService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
