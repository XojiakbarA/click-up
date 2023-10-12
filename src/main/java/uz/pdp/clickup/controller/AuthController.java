package uz.pdp.clickup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.dto.request.auth.JoinRequest;
import uz.pdp.clickup.dto.request.auth.LoginRequest;
import uz.pdp.clickup.dto.request.auth.RegisterRequest;
import uz.pdp.clickup.dto.request.auth.VerifyRequest;
import uz.pdp.clickup.dto.response.AuthResponse;
import uz.pdp.clickup.service.AuthService;
import uz.pdp.clickup.service.WorkspaceUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private WorkspaceUserService workspaceUserService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);

        return new AuthResponse("Registration completed successfully. Please check and verify your email");
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse verify(@Valid @RequestBody VerifyRequest request) {
        authService.verify(request);

        return new AuthResponse("Verification completed successfully. Please login");
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse join(@Valid @RequestBody JoinRequest request) {
        workspaceUserService.join(request);

        return new AuthResponse("You joined successfully");
    }
}
