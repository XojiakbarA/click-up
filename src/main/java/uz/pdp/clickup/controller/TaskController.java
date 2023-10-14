package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.clickup.dto.request.TaskRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.TaskView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.TaskAttachmentService;
import uz.pdp.clickup.service.TaskService;

@Validated
@RestController
@RequestMapping("/lists")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskAttachmentService taskAttachmentService;

    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByListId(@PathVariable Long id) {
        List<TaskView> tasks = taskService.getAllByListId(id);

        return new Response(HttpStatus.OK.name(), tasks);
    }

    @Validated(OnCreate.class)
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody TaskRequest request) {
        TaskView task = taskService.create(request);

        return new Response(HttpStatus.CREATED.name(), task);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody TaskRequest request, @PathVariable Long id) {
        TaskView task = taskService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), task);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        taskService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }

    @PutMapping("/tasks/{id}/assign-user/{taskUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setAssignedUser(@PathVariable Long id, @PathVariable Long taskUserId) {
        TaskView task = taskService.setAssignedUser(id, taskUserId);

        return new Response(HttpStatus.OK.name(), task);
    }

    @DeleteMapping("/tasks/{id}/assign-user")
    @ResponseStatus(HttpStatus.OK)
    public Response removeAssignedUser(@PathVariable Long id) {
        TaskView task = taskService.removeAssignedUser(id);

        return new Response(HttpStatus.OK.name(), task);
    }

    @DeleteMapping("/tasks/attachment/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteTaskAttachmentById(@PathVariable Long id) {
        taskAttachmentService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
