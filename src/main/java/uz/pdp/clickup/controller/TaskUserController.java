package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.TaskUserView;
import uz.pdp.clickup.service.TaskUserService;

@RestController
@RequestMapping("/tasks")
public class TaskUserController {
    @Autowired
    private TaskUserService taskUserService;

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByTaskId(@PathVariable Long id) {
        List<TaskUserView> taskUsers = taskUserService.getAllByTaskId(id);

        return new Response(HttpStatus.OK.name(), taskUsers);
    }

    @PutMapping("/{id}/users/{listUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response addTaskUser(@PathVariable Long id, @PathVariable Long listUserId) {
        TaskUserView taskUser = taskUserService.create(id, listUserId);

        return new Response(HttpStatus.OK.name(), taskUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response removeTaskUser(@PathVariable Long id) {
        taskUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
