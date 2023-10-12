package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkspaceView extends BaseView {
    private String name;
    private ColorView color;
    private UserView owner;
    private Character initialLetter;
}
