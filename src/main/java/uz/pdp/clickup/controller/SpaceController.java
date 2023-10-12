package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.clickup.dto.request.SpaceRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.SpaceView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.SpaceService;

@Validated
@RestController
@RequestMapping
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @GetMapping("/workspaces/{id}/spaces")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllSpacesByWorkspaceId(@PathVariable Long id) {
        List<SpaceView> spaces = spaceService.getAllSpacesByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), spaces);
    }

    @GetMapping("/spaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        SpaceView space = spaceService.getById(id);

        return new Response(HttpStatus.OK.name(), space);
    }

    @Validated(OnCreate.class)
    @PostMapping("/spaces")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody SpaceRequest request) {
        SpaceView space = spaceService.create(request);

        return new Response(HttpStatus.CREATED.name(), space);
    }

    @PutMapping("/spaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody SpaceRequest request, @PathVariable Long id) {
        SpaceView space = spaceService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), space);
    }

    @PutMapping("/spaces/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        spaceService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
