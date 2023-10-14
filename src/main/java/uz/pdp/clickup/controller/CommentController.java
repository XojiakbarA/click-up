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
import uz.pdp.clickup.dto.request.CommentRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.CommentView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.CommentService;

@Validated
@RestController
@RequestMapping("/tasks")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@PathVariable Long id) {
        List<CommentView> comments = commentService.getAllByTaskId(id);

        return new Response(HttpStatus.OK.name(), comments);
    }

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        CommentView comment = commentService.getById(id);

        return new Response(HttpStatus.OK.name(), comment);
    }

    @Validated(OnCreate.class)
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CommentRequest request) {
        CommentView comment = commentService.create(request);

        return new Response(HttpStatus.CREATED.name(), comment);
    }

    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody CommentRequest request, @PathVariable Long id) {
        CommentView comment = commentService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), comment);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        commentService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
