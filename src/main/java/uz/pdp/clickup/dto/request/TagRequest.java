package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.marker.OnCreate;

@Data
public class TagRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be empty", groups = OnCreate.class)
    private String name;

    @NotNull(message = "workspaceId must not be null", groups = OnCreate.class)
    @Positive(message = "workspaceId must be positive")
    private Long workspaceId;

    @Positive(message = "colorId must be positive")
    private Long colorId;

    @Positive(message = "taskId must be positive")
    private Long taskId;
}
