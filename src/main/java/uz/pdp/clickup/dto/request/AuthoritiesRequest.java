package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.clickup.enums.AuthorityType;
import uz.pdp.clickup.validator.IsValidEnum;

import java.util.Set;

@Data
public class AuthoritiesRequest {
    @NotNull(message = "authorities must not be null")
    @NotEmpty(message = "authorities must not be empty")
    private Set<
        @NotNull(message = "authority must not be null")
        @NotBlank(message = "authority must not be empty")
        @IsValidEnum(enumClazz = AuthorityType.class) String> authorities;
}
