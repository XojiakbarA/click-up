package uz.pdp.clickup.dto.view;

import lombok.Data;

@Data
public class TaskUserView {
    private TaskView task;
    private UserView person;
}
