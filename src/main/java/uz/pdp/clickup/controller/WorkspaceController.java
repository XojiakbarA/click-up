package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import uz.pdp.clickup.dto.request.WorkspaceRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.WorkspaceService;

import java.util.List;

@Validated
@RestController
@RequestMapping
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/users/{id}/workspaces")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByUserId(@PathVariable Long id) {
        List<WorkspaceView> workspaces = workspaceService.getAllByUserId(id);

        return new Response(HttpStatus.OK.name(), workspaces);
    }

    @GetMapping("/workspaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        WorkspaceView workspace = workspaceService.getById(id);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @Validated(OnCreate.class)
    @PostMapping("/workspaces")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody WorkspaceRequest request) {
        WorkspaceView workspace = workspaceService.create(request);

        return new Response(HttpStatus.CREATED.name(), workspace);
    }

    @PutMapping("/workspaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody WorkspaceRequest request,
                               @PathVariable Long id) {
        WorkspaceView workspace = workspaceService.update(request, id);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @PutMapping("/workspaces/{id}/owner/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    public Response setOwnerById(@PathVariable Long id,
                                 @PathVariable Long ownerId) {
        WorkspaceView workspace = workspaceService.setOwnerById(id, ownerId);

        return new Response(HttpStatus.OK.name(), workspace);
    }

    @DeleteMapping("/workspaces/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        workspaceService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
