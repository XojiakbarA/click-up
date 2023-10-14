package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.marker.OnCreate;

@Data
public class CommentRequest {
    @NotNull(message = "content must not be null", groups = OnCreate.class)
    @NotBlank(message = "content must not be empty", groups = OnCreate.class)
    private String content;

    @NotNull(message = "taskId must not be null", groups = OnCreate.class)
    @Positive(message = "taskId must be positive")
    private Long taskId;
}
