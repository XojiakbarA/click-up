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
import uz.pdp.clickup.dto.request.ClickAppRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.ClickAppView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.ClickAppService;

@Validated
@RestController
@RequestMapping("/click-apps")
public class ClickAppController {
    @Autowired
    private ClickAppService clickAppService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        List<ClickAppView> clickApps = clickAppService.getAll();

        return new Response(HttpStatus.OK.name(), clickApps);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        ClickAppView clickApp = clickAppService.getById(id);

        return new Response(HttpStatus.OK.name(), clickApp);
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ClickAppRequest request) {
        ClickAppView clickApp = clickAppService.create(request);

        return new Response(HttpStatus.CREATED.name(), clickApp);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody ClickAppRequest request, @PathVariable Long id) {
        ClickAppView clickApp = clickAppService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), clickApp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        clickAppService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
