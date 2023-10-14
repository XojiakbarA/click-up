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
import uz.pdp.clickup.dto.request.ViewRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ViewView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.ViewService;

@Validated
@RestController
@RequestMapping("/views")
public class ViewController {
    @Autowired
    private ViewService viewService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        List<ViewView> views = viewService.getAll();

        return new Response(HttpStatus.OK.name(), views);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        ViewView view = viewService.getById(id);

        return new Response(HttpStatus.OK.name(), view);
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ViewRequest request) {
        ViewView view = viewService.create(request);

        return new Response(HttpStatus.CREATED.name(), view);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody ViewRequest request, @PathVariable Long id) {
        ViewView view = viewService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), view);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        viewService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
