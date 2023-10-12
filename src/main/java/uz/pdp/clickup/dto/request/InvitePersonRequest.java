package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InvitePersonRequest {
    @NotNull(message = "workspaceId must not be null")
    @Positive(message = "workspaceId must be positive")
    private Long workspaceId;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;
}
