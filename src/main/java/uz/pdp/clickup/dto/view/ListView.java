package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AccessType;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListView extends BaseView {
    private String name;
    private FolderView folder;
    private ColorView color;
    private Boolean archived;
    private AccessType accessType;
}
