package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.auth.LoginRequest;
import uz.pdp.clickup.dto.request.auth.RegisterRequest;
import uz.pdp.clickup.dto.request.auth.VerifyRequest;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.enums.RoleType;
import uz.pdp.clickup.security.JWTProvider;
import uz.pdp.clickup.service.AuthService;
import uz.pdp.clickup.service.MessageService;
import uz.pdp.clickup.service.UserService;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtProvider.generateToken(Map.of("roles", userDetails.getAuthorities()), userDetails);
    }

    @Override
    public void register(RegisterRequest request) {
        User user = userService.create(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                request.getColorId(),
                RoleType.USER.name()
        );

        messageService.sendVerifyCode(user.getEmail(), user.getVerifyCode());
    }

    @Override
    public void verify(VerifyRequest request) {
        User user = userService.findByEmail(request.getEmail());

        if (!user.getVerifyCode().equals(request.getVerifyCode())) {
            throw new MailAuthenticationException("Verification Failed");
        }

        user.setEnabled(true);
        user.setVerifyCode(null);

        userService.save(user);
    }

}
