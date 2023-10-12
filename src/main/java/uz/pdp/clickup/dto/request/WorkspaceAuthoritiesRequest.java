package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.clickup.enums.WorkspaceAuthorityType;
import uz.pdp.clickup.validator.IsValidEnum;

import java.util.Set;

@Data
public class WorkspaceAuthoritiesRequest {
    @NotNull(message = "workspaceAuthorities must not be null")
    @NotEmpty(message = "workspaceAuthorities must not be empty")
    private Set<
            @NotNull(message = "workspaceAuthority must not be null")
            @NotBlank(message = "workspaceAuthority must not be empty")
            @IsValidEnum(enumClazz = WorkspaceAuthorityType.class) String> workspaceAuthorities;
}
