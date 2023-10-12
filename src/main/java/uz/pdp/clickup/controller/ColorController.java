package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.ColorRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ColorView;
import uz.pdp.clickup.service.ColorService;

@RestController
@RequestMapping("/colors")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<ColorView> colors = colorService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), colors);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        ColorView color = colorService.getById(id);

        return new Response(HttpStatus.OK.name(), color);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ColorRequest request) {
        ColorView color = colorService.create(request);

        return new Response(HttpStatus.CREATED.name(), color);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody ColorRequest request, @PathVariable Long id) {
        ColorView color = colorService.update(request, id);

        return new Response(HttpStatus.OK.name(), color);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        colorService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
