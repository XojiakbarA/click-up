package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.SpaceUserView;
import uz.pdp.clickup.entity.SpaceUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, SpaceMapper.class, UserMapper.class})
public interface SpaceUserMapper {
    SpaceUserView mapToView(SpaceUser spaceUser);
}
