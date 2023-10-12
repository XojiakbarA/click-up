package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.UserAuditorView;
import uz.pdp.clickup.entity.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserAuditorMapper {
    @Mapping(target = "roleName", source = "user.role.name")
    UserAuditorView mapToAuditorView(User user);
}
