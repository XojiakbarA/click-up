package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.AuthoritiesRequest;
import uz.pdp.clickup.dto.request.RoleRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.RoleView;
import uz.pdp.clickup.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<RoleView> roles = roleService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), roles);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        RoleView role = roleService.getById(id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody RoleRequest request) {
        RoleView role = roleService.create(request);

        return new Response(HttpStatus.CREATED.name(), role);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody RoleRequest request, @PathVariable Long id) {
        RoleView role = roleService.update(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PutMapping("/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response addAuthorities(@Valid @RequestBody AuthoritiesRequest request, @PathVariable Long id) {
        RoleView role = roleService.addAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @DeleteMapping("/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response removeAuthorities(@Valid @RequestBody AuthoritiesRequest request, @PathVariable Long id) {
        RoleView role = roleService.removeAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        roleService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
