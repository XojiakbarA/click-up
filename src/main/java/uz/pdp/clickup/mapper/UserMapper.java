package uz.pdp.clickup.mapper;

import org.mapstruct.*;
import uz.pdp.clickup.dto.view.UserView;
import uz.pdp.clickup.entity.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = { UserAuditorMapper.class, RoleMapper.class })
public interface UserMapper {
    UserView mapToView(User user);
}
