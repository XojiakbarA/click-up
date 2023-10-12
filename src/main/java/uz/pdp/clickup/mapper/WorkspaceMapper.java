package uz.pdp.clickup.mapper;

import org.mapstruct.*;
import uz.pdp.clickup.dto.view.WorkspaceView;
import uz.pdp.clickup.entity.Workspace;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class})
public interface WorkspaceMapper {
    WorkspaceView mapToView(Workspace workspace);
}
