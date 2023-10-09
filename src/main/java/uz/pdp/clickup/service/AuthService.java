package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.auth.LoginRequest;
import uz.pdp.clickup.dto.request.auth.RegisterRequest;
import uz.pdp.clickup.dto.request.auth.VerifyRequest;

public interface AuthService {
    String login(LoginRequest request);

    void register(RegisterRequest request);

    void verify(VerifyRequest request);
}
