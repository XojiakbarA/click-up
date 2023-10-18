package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.ChecklistView;
import uz.pdp.clickup.entity.Checklist;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, TaskMapper.class})
public interface ChecklistMapper {
    ChecklistView mapToView(Checklist checklist);
}
