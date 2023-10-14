package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.marker.OnCreate;

@Data
public class StatusRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be empty", groups = OnCreate.class)
    private String name;

    @NotNull(message = "listId must not be null", groups = OnCreate.class)
    @Positive(message = "listId must be positive")
    private Long listId;

    @Positive(message = "colorId must be positive")
    private Long colorId;
}
