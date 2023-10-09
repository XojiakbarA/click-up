package uz.pdp.clickup.dto.view;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserView {
    private Long id;
    private String fullName;
    private String email;
    private RoleView role;
    private Boolean accountNonLocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAuditorView createdBy;
    private UserAuditorView updatedBy;
}
