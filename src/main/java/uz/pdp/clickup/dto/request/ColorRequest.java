package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ColorRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    private String name;

    @NotNull(message = "hexCode must not be null")
    @NotBlank(message = "hexCode must not be empty")
    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", message = "hexCode must be hex code")
    private String hexCode;
}
