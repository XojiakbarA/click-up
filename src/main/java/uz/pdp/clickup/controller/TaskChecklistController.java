package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.ChecklistRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ChecklistView;
import uz.pdp.clickup.service.ChecklistService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskChecklistController {
    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/{id}/checklists")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByTaskId(@PathVariable Long id) {
        List<ChecklistView> checklists = checklistService.getAllByTaskId(id);

        return new Response(HttpStatus.OK.name(), checklists);
    }

    @PostMapping("/checklists")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ChecklistRequest request) {
        ChecklistView checklist = checklistService.create(request);

        return new Response(HttpStatus.CREATED.name(), checklist);
    }

    @PutMapping("/checklists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody ChecklistRequest request, @PathVariable Long id) {
        ChecklistView checklist = checklistService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), checklist);
    }

    @DeleteMapping("/checklists/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        checklistService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
