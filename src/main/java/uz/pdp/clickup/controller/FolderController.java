package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.FolderRequest;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.dto.view.FolderView;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.service.FolderService;

import java.util.List;

@Validated
@RestController
@RequestMapping
public class FolderController {
    @Autowired
    private FolderService folderService;

    @GetMapping("/spaces/{id}/folders")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllBySpaceId(@PathVariable Long id) {
        List<FolderView> folders = folderService.getAllBySpaceId(id);

        return new Response(HttpStatus.OK.name(), folders);
    }

    @Validated(OnCreate.class)
    @PostMapping("/spaces/folders")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody FolderRequest request) {
        FolderView folder = folderService.create(request);

        return new Response(HttpStatus.CREATED.name(), folder);
    }

    @PutMapping("/spaces/folders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateById(@Valid @RequestBody FolderRequest request, @PathVariable Long id) {
        FolderView folder = folderService.updateById(request, id);

        return new Response(HttpStatus.OK.name(), folder);
    }

    @DeleteMapping("/spaces/folders/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response updateById(@PathVariable Long id) {
        folderService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
