package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.UserRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.UserView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.UserService;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<UserView> users = userService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        UserView user = userService.getById(id);

        return new Response(HttpStatus.OK.name(), user);
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody UserRequest request) {
        UserView user = userService.create(request);

        return new Response(HttpStatus.CREATED.name(), user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        UserView user = userService.update(request, id);

        return new Response(HttpStatus.OK.name(), user);
    }

    @PutMapping("/{id}/lock")
    @ResponseStatus(HttpStatus.OK)
    public Response lock(@PathVariable Long id) {
        userService.lock(id);

        return new Response(HttpStatus.OK.name());
    }

    @PutMapping("/{id}/unlock")
    @ResponseStatus(HttpStatus.OK)
    public Response unlock(@PathVariable Long id) {
        userService.unlock(id);

        return new Response(HttpStatus.OK.name());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
