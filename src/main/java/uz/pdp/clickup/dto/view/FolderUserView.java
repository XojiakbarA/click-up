package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.TaskAuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
public class FolderUserView extends BaseView {
    private FolderView folder;
    private UserView person;
    private TaskAuthorityType taskAuthority;
}
