package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemView extends BaseView {
    private String name;
    private ChecklistView checklist;
    private TaskUserView assignedUser;
    private Boolean completed;
}
