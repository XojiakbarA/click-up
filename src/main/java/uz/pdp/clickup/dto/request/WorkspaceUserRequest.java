package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.marker.OnCreate;

@Data
public class WorkspaceUserRequest {
    @NotNull(message = "workspaceId must not be null", groups = OnCreate.class)
    @Positive(message = "workspaceId must be positive")
    private Long workspaceId;

    @NotNull(message = "personId must not be null", groups = OnCreate.class)
    @Positive(message = "personId must be positive")
    private Long personId;
}
