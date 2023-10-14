package uz.pdp.clickup.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.clickup.enums.AccessType;
import uz.pdp.clickup.marker.OnCreate;
import uz.pdp.clickup.validator.IsValidEnum;

@Data
public class TaskRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be empty", groups = OnCreate.class)
    private String name;
    
    private String description;

    @NotNull(message = "statusId must not be null", groups = OnCreate.class)
    @Positive(message = "statusId must be positive")
    private Long statusId;

    @NotNull(message = "listId must not be null", groups = OnCreate.class)
    @Positive(message = "listId must be positive")
    private Long listId;

    @Positive(message = "priorityId must be positive")
    private Long priorityId;

    @Positive(message = "parentTaskId must be positive")
    private Long parentTaskId;

    @IsValidEnum(enumClazz = AccessType.class)
    private String accessType;

    private LocalDate startDate;
    
    private LocalTime startTime;
    
    @Future
    private LocalDate endDate;
    
    @Future
    private LocalTime endTime;

    private Set<MultipartFile> files;

    private Set<@Positive(message = "personId must be positive") Long> personIds;
}
