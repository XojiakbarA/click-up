package uz.pdp.clickup.dto.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuditorView {
    private Long id;
    private String email;
    private String roleName;
}
