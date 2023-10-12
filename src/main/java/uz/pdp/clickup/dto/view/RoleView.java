package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AuthorityType;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleView extends BaseView {
    private String name;
    private Set<AuthorityType> authorities;
}
