package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.WorkspaceUserView;
import uz.pdp.clickup.entity.WorkspaceUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, WorkspaceMapper.class, UserMapper.class, WorkspaceRoleMapper.class})
public interface WorkspaceUserMapper {
    WorkspaceUserView mapToView(WorkspaceUser workspaceUser);
}
