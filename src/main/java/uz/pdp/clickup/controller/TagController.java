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
import uz.pdp.clickup.dto.request.TagRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.TagView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.TagService;

@Validated
@RestController
@RequestMapping("/workspaces")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/{id}/tags")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@PathVariable Long id) {
        List<TagView> tags = tagService.getAllByWorkspaceId(id);

        return new Response(HttpStatus.OK.name(), tags);
    }

    @GetMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        TagView tag = tagService.getById(id);

        return new Response(HttpStatus.OK.name(), tag);
    }

    @Validated(OnCreate.class)
    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody TagRequest request) {
        TagView tag = tagService.create(request);

        return new Response(HttpStatus.CREATED.name(), tag);
    }

    @PutMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody TagRequest request, @PathVariable Long id) {
        TagView tag = tagService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), tag);
    }

    @DeleteMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        tagService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
