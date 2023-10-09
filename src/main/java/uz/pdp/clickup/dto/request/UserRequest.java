package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.entity.Attachment;
import uz.pdp.clickup.marker.OnCreate;

@Data
public class UserRequest {
    @NotNull(message = "fullName must not be null", groups = OnCreate.class)
    @NotBlank(message = "fullName must not be empty", groups = OnCreate.class)
    private String fullName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be empty", groups = OnCreate.class)
    private String email;

    @NotNull(message = "password must not be null", groups = OnCreate.class)
    @NotBlank(message = "password must not be empty", groups = OnCreate.class)
    private String password;

    @NotNull(message = "roleId must not be null", groups = OnCreate.class)
    @Positive(message = "roleId must be positive")
    private Long roleId;

    @Positive(message = "roleId must be positive")
    private Long colorId;

    private Attachment avatar;
}
