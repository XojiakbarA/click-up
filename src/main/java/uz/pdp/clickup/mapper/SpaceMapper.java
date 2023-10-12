package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.SpaceView;
import uz.pdp.clickup.entity.Space;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, ColorMapper.class, WorkspaceMapper.class})
public interface SpaceMapper {
    SpaceView mapToView(Space space);
}
