package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.StatusView;
import uz.pdp.clickup.entity.Status;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class, ListMapper.class, ColorMapper.class})
public interface StatusMapper {
    StatusView mapToView(Status status);
}
