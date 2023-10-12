package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAuditorView extends BaseView {
    private Long id;
    private String email;
    private String roleName;
}
