package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    private String name;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
}
