package uz.pdp.clickup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickup.dto.response.Response;
import uz.pdp.clickup.enums.AuthorityType;

import java.util.Set;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        return new Response(HttpStatus.OK.name(), Set.of(AuthorityType.values()));
    }
}
