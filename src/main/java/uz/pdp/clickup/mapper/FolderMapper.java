package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.FolderView;
import uz.pdp.clickup.entity.Folder;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, SpaceMapper.class, ColorMapper.class})
public interface FolderMapper {
    FolderView mapToView(Folder folder);
}
