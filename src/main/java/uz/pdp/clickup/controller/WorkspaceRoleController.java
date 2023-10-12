package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.WorkspaceAuthoritiesRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.service.WorkspaceRoleService;

@Validated
@RestController
@RequestMapping("/workspace-roles")
public class WorkspaceRoleController {
    @Autowired
    private WorkspaceRoleService workspaceRoleService;

    @PutMapping("/{id}/workspace-authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response addWorkspaceAuthorities(@Valid @RequestBody WorkspaceAuthoritiesRequest request,
                                            @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.addWorkspaceAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), workspaceRole);
    }

    @DeleteMapping("/{id}/workspace-authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response removeWorkspaceAuthorities(@Valid @RequestBody WorkspaceAuthoritiesRequest request,
                                               @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceRoleService.removeWorkspaceAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), workspaceRole);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteWorkspaceRoleById(@PathVariable Long id) {
        workspaceRoleService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
