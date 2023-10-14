package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.PriorityView;
import uz.pdp.clickup.entity.Priority;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, ColorMapper.class})
public interface PriorityMapper {
    PriorityView mapToView(Priority priority);
}
