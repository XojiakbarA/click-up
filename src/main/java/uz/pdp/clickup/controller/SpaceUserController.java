package uz.pdp.clickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.SpaceUserView;
import uz.pdp.clickup.service.SpaceUserService;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceUserController {
    @Autowired
    private SpaceUserService spaceUserService;

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllUsersBySpaceId(@PathVariable Long id) {
        List<SpaceUserView> spaceUsers = spaceUserService.getAllBySpaceId(id);

        return new Response(HttpStatus.OK.name(), spaceUsers);
    }

    @PutMapping("/{id}/users/{workspaceUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response addSpaceUser(@PathVariable Long id, @PathVariable Long workspaceUserId) {
        SpaceUserView spaceUser = spaceUserService.create(id, workspaceUserId);

        return new Response(HttpStatus.OK.name(), spaceUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response removeSpaceUser(@PathVariable Long id) {
        spaceUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
