package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.ListView;
import uz.pdp.clickup.entity.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, FolderMapper.class, ColorMapper.class})
public interface ListMapper {
    ListView mapToView(List list);
}
