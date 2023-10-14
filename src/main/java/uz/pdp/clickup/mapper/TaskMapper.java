package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.TaskView;
import uz.pdp.clickup.entity.Task;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, StatusMapper.class, ListMapper.class, PriorityMapper.class})
public interface TaskMapper {
    TaskView mapToView(Task task);
}
