package uz.pdp.clickup.mapper;

import org.mapstruct.*;
import uz.pdp.clickup.dto.view.WorkspaceRoleView;
import uz.pdp.clickup.entity.WorkspaceRole;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, WorkspaceMapper.class})
public interface WorkspaceRoleMapper {
    WorkspaceRoleView mapToView(WorkspaceRole workspaceRole);
}
