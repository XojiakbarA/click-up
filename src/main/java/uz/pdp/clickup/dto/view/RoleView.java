package uz.pdp.clickup.dto.view;

import lombok.Builder;
import lombok.Data;
import uz.pdp.clickup.enums.AuthorityType;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class RoleView {
    private Long id;
    private String name;
    private Set<AuthorityType> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAuditorView createdBy;
    private UserAuditorView updatedBy;
}
