package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.auth.JoinRequest;
import uz.pdp.clickup.dto.request.auth.LoginRequest;
import uz.pdp.clickup.dto.request.auth.RegisterRequest;
import uz.pdp.clickup.dto.request.auth.VerifyRequest;
import uz.pdp.clickup.entity.User;

public interface AuthService {
    User getAuthUser();

    String login(LoginRequest request);

    void register(RegisterRequest request);

    void verify(VerifyRequest request);
}
