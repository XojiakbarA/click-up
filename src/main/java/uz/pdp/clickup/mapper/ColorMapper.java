package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.clickup.dto.view.ColorView;
import uz.pdp.clickup.entity.Color;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class})
public interface ColorMapper {
    ColorView mapToView(Color color);
}
