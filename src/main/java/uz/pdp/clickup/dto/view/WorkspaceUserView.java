package uz.pdp.clickup.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkspaceUserView extends BaseView {
    private WorkspaceView workspace;
    private UserView person;
    private WorkspaceRoleView workspaceRole;
    private LocalDateTime invitedAt;
    private LocalDateTime joinedAt;
}
