package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ListUserView;
import uz.pdp.clickup.service.ListUserService;

@RestController
@RequestMapping("/lists")
public class ListUserController {
    @Autowired
    private ListUserService listUserService;

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByListId(@PathVariable Long id) {
        List<ListUserView> listUsers = listUserService.getAllByListId(id);

        return new Response(HttpStatus.OK.name(), listUsers);
    }

    @PutMapping("/{id}/users/{folderUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Response addListUser(@PathVariable Long id, @PathVariable Long folderUserId) {
        ListUserView listUser = listUserService.create(id, folderUserId);

        return new Response(HttpStatus.OK.name(), listUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response removeListUser(@PathVariable Long id) {
        listUserService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
