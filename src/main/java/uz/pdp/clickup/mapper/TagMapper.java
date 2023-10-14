package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.TagView;
import uz.pdp.clickup.entity.Tag;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, WorkspaceMapper.class, ColorMapper.class})
public interface TagMapper {
    TagView mapToView(Tag tag);
}
