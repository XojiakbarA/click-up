package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class WorkspaceRoleRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    private String name;

    @NotNull(message = "workspaceId must not be null")
    @Positive(message = "workspaceId must be positive")
    private Long workspaceId;

    @Positive(message = "extendsWorkspaceRoleId must be positive")
    private Long extendsWorkspaceRoleId;
}
