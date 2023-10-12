package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.WorkspaceRequest;
import uz.pdp.clickup.dto.request.WorkspaceRoleRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.WorkspaceRoleService;
import uz.pdp.clickup.service.WorkspaceService;
import uz.pdp.clickup.service.WorkspaceUserService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private WorkspaceRoleService workspaceRoleService;
    @Autowired
    private WorkspaceUserService workspaceUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByAuthUserId() {
        List<WorkspaceView> workspaces = workspaceService.getAllByAuthUserId();

        return new Response(HttpStatus.OK.name(), workspaces);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        WorkspaceView workspace = workspaceService.getById(id);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody WorkspaceRequest request) {
        WorkspaceView workspace = workspaceService.create(request);

        return new Response(HttpStatus.CREATED.name(), workspace);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody WorkspaceRequest request,
                               @PathVariable Long id) {
        WorkspaceView workspace = workspaceService.update(request, id);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @PutMapping("/{id}/owner/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setOwnerById(@PathVariable Long id,
                                 @PathVariable Long ownerId) {
        WorkspaceView workspace = workspaceService.setOwnerById(id, ownerId);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        workspaceService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }





    @GetMapping("/{id}/workspace-roles")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllWorkspaceRolesByWorkspaceId(@PathVariable Long id) {
        List<WorkspaceRoleView> workspaceRoles = workspaceRoleService.getAllByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), workspaceRoles);
    }

    @PostMapping("/{id}/workspace-roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createWorkspaceRole(@Valid @RequestBody WorkspaceRoleRequest request,
                                        @PathVariable Long id) {
        WorkspaceRoleView workspaceRole = workspaceService.createWorkspaceRole(request, id);

        return new Response(HttpStatus.CREATED.name(), workspaceRole);
    }




    @GetMapping("/{id}/members")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllMembersByWorkspaceId(@PathVariable Long id) {
        List<WorkspaceUserView> workspaceUsers = workspaceUserService.getAllMembersByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), workspaceUsers);
    }

    @GetMapping("/{id}/guests")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllGuestsByWorkspaceId(@PathVariable Long id) {
        List<WorkspaceUserView> workspaceUsers = workspaceUserService.getAllGuestsByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), workspaceUsers);
    }

    @PostMapping("/{id}/workspace-users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Response invitePersonByEmail(@PathVariable Long id,
                                        @PathVariable String email) {
        workspaceService.invitePersonByEmail(id, email);

        return new Response("Workspace User invited successfully");
    }
}
