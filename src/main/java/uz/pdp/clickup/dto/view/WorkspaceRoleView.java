package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkspaceRoleView extends BaseView {
    private String name;
    private WorkspaceView workspace;
}
