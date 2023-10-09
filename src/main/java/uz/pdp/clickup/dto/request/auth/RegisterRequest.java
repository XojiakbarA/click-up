package uz.pdp.clickup.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.entity.Attachment;

@Data
public class RegisterRequest {
    @NotNull(message = "fullName must not be null")
    @NotBlank(message = "fullName must not be empty")
    private String fullName;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be empty")
    private String password;

    @Positive(message = "colorId must be positive")
    private Long colorId;

    private Attachment avatar;
}
