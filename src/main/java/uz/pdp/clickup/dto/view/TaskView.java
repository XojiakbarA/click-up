package uz.pdp.clickup.dto.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskView extends BaseView {
    private String name;
    private String description;
    private StatusView status;
    private ListView list;
    private PriorityView priority;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private LocalDateTime activatedAt;
}
