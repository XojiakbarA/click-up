package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.TaskAuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListUserView extends BaseView {
    private ListView list;
    private UserView person;
    private TaskAuthorityType taskAuthority;
}
