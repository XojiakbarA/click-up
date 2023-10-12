package uz.pdp.clickup.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JoinRequest {
    @NotNull(message = "workspaceUserId must not be null")
    @Positive(message = "workspaceUserId must be positive")
    private Long workspaceUserId;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "joinCode must not be null")
    @NotBlank(message = "joinCode must not be empty")
    private String joinCode;
}
