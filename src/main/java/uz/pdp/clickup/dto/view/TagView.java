package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagView extends BaseView {
    private String name;
    private WorkspaceView workspace;
    private ColorView color;
}
