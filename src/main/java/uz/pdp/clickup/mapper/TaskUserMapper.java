package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.TaskUserView;
import uz.pdp.clickup.entity.TaskUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, TaskMapper.class, UserMapper.class})
public interface TaskUserMapper {
    TaskUserView mapToView(TaskUser taskUser);
}
