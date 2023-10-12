package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserView extends BaseView {
    private String fullName;
    private String email;
    private RoleView role;
    private Boolean accountNonLocked;
}
