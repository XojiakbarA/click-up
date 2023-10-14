package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.ListUserView;
import uz.pdp.clickup.entity.ListUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, ListMapper.class, UserMapper.class})
public interface ListUserMapper {
    ListUserView mapToView(ListUser listUser);
}
