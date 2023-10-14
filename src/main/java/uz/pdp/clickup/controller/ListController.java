package uz.pdp.clickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.clickup.dto.request.ListRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ListView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.ListService;

@RestController
@RequestMapping
public class ListController {
    @Autowired
    private ListService listService;

    @GetMapping("/folders/{id}/lists")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByFolderId(@PathVariable Long id) {
        List<ListView> lists = listService.getAllByFolderId(id);

        return new Response(HttpStatus.OK.name(), lists);
    }

    @Validated(OnCreate.class)
    @PostMapping("/folders/lists")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ListRequest request) {
        ListView list = listService.create(request);

        return new Response(HttpStatus.CREATED.name(), list);
    }

    @PutMapping("/folders/lists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody ListRequest request, @PathVariable Long id) {
        ListView list = listService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), list);
    }

    @DeleteMapping("/folders/lists/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response updateById(@PathVariable Long id) {
        listService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
