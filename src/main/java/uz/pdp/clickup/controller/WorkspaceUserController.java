package uz.pdp.clickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.service.WorkspaceUserService;

@RestController
@RequestMapping("/workspace-users")
public class WorkspaceUserController {
    @Autowired
    private WorkspaceUserService workspaceUserService;

    @PutMapping("/{workspaceUserId}/workspace-role/{workspaceRoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setWorkspaceRole(@PathVariable Long workspaceUserId, @PathVariable Long workspaceRoleId) {
        WorkspaceUserView workspaceUser = workspaceUserService.setWorkspaceRole(workspaceUserId, workspaceRoleId);

        return new Response(HttpStatus.OK.name(), workspaceUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteWorkspaceUserById(@PathVariable Long id) {
        workspaceUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
