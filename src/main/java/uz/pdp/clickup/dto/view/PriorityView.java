package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.PriorityType;

@EqualsAndHashCode(callSuper = true)
@Data
public class PriorityView extends BaseView {
    private PriorityType type;
    private ColorView color;
}
