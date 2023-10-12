package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AccessType;

@EqualsAndHashCode(callSuper = true)
@Data
public class FolderView extends BaseView {
    private String name;
    private SpaceView space;
    private ColorView color;
    private Boolean archived;
    private AccessType accessType;
}
