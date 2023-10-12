package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AccessType;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpaceView extends BaseView {
    private String name;
    private ColorView color;
    private WorkspaceView workspace;
    private AccessType accessType;
    private Character initialLetter;
}
