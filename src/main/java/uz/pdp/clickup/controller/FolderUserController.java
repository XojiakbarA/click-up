package uz.pdp.clickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.FolderUserView;
import uz.pdp.clickup.service.FolderUserService;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderUserController {
    @Autowired
    private FolderUserService folderUserService;

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByFolderId(@PathVariable Long id) {
        List<FolderUserView> folderUsers = folderUserService.getAllByFolderId(id);

        return new Response(HttpStatus.OK.name(), folderUsers);
    }

    @PutMapping("/{id}/users/{spaceUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response addFolderUser(@PathVariable Long id, @PathVariable Long spaceUserId) {
        FolderUserView folderUser = folderUserService.create(id, spaceUserId);

        return new Response(HttpStatus.OK.name(), folderUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response removeFolderUser(@PathVariable Long id) {
        folderUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
