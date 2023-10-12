package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.RoleView;
import uz.pdp.clickup.entity.Role;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class})
public interface RoleMapper {
    RoleView mapToView(Role role);
}
