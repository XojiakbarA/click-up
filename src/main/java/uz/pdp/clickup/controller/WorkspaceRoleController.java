package uz.pdp.clickup.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.WorkspaceAuthoritiesRequest;
import uz.pdp.clickup.dto.request.WorkspaceRoleRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.WorkspaceRoleService;

@Validated
@RestController
@RequestMapping("/workspaces")
public class WorkspaceRoleController {
    @Autowired
    private WorkspaceRoleService workspaceRoleService;

    @GetMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllWorkspaceRolesByWorkspaceId(@PathVariable Long id) {
        List<WorkspaceRoleView> workspaceRoles = workspaceRoleService.getAllByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), workspaceRoles);
    }

    @Validated(OnCreate.class)
    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody WorkspaceRoleRequest request) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.create(request);

        return new Response(HttpStatus.CREATED.name(), workspaceRole);
    }

    @PutMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody WorkspaceRoleRequest request,
                                @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.update(request, id);

        return new Response(HttpStatus.OK.name(), workspaceRole);
    }

    @PutMapping("/roles/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response addWorkspaceAuthorities(@Valid @RequestBody WorkspaceAuthoritiesRequest request,
                                            @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.addWorkspaceAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), workspaceRole);
    }

    @DeleteMapping("/roles/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response removeWorkspaceAuthorities(@Valid @RequestBody WorkspaceAuthoritiesRequest request,
                                               @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.removeWorkspaceAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), workspaceRole);
    }

    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        workspaceRoleService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
