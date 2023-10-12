package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import uz.pdp.clickup.dto.request.InvitePersonRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.service.WorkspaceUserService;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceUserController {
    @Autowired
    private WorkspaceUserService workspaceUserService;

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

    @PostMapping("/invite")
    @ResponseStatus(HttpStatus.OK)
    public Response invitePersonByEmail(@Valid @RequestBody InvitePersonRequest request) {
        workspaceUserService.invitePersonByEmail(request);

        return new Response("Workspace User invited successfully");
    }

    @PutMapping("/users/{workspaceUserId}/role/{workspaceRoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setWorkspaceRole(@PathVariable Long workspaceUserId, @PathVariable Long workspaceRoleId) {
        WorkspaceUserView workspaceUser = workspaceUserService.setWorkspaceRole(workspaceUserId, workspaceRoleId);

        return new Response(HttpStatus.OK.name(), workspaceUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteWorkspaceUserById(@PathVariable Long id) {
        workspaceUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
