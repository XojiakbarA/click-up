package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.ItemView;
import uz.pdp.clickup.entity.Item;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, ChecklistMapper.class, TaskUserMapper.class})
public interface ItemMapper {
    ItemView mapToView(Item item);
}
