package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.StatusType;

@EqualsAndHashCode(callSuper = true)
@Data
public class StatusView extends BaseView {
    private String name;
    private ListView list;
    private ColorView color;
    private StatusType type;
}
