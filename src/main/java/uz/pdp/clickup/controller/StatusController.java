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
import uz.pdp.clickup.dto.request.StatusRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.StatusView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.StatusService;

@Validated
@RestController
@RequestMapping("/lists")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/{id}/statuses")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByListId(@PathVariable Long id) {
        List<StatusView> statuses = statusService.getAllByListId(id);

        return new Response(HttpStatus.OK.name(), statuses);
    }

    @Validated(OnCreate.class)
    @PostMapping("/statuses")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody StatusRequest request) {
        StatusView status = statusService.create(request);

        return new Response(HttpStatus.CREATED.name(), status);
    }

    @PutMapping("/statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody StatusRequest request, @PathVariable Long id) {
        StatusView status = statusService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), status);
    }

    @DeleteMapping("/statuses/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response updateById(@PathVariable Long id) {
        statusService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
