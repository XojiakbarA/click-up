package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.FolderUserView;
import uz.pdp.clickup.entity.FolderUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, FolderMapper.class, UserMapper.class})
public interface FolderUserMapper {
    FolderUserView mapToView(FolderUser folderUser);
}
