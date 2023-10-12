package uz.pdp.clickup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.validator.IsValidEnum;

import java.util.Set;

@Data
public class FolderRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be empty", groups = OnCreate.class)
    private String name;

    @NotNull(message = "spaceId must not be null", groups = OnCreate.class)
    @Positive(message = "spaceId must be positive")
    private Long spaceId;

    @Positive(message = "colorId must be positive")
    private Long colorId;

    @IsValidEnum(enumClazz = AccessType.class)
    private String accessType;

    private Set<@Positive(message = "personId must be positive") Long> personIds;
}
