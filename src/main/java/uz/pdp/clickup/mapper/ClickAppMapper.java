package uz.pdp.clickup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import uz.pdp.clickup.dto.view.ClickAppView;
import uz.pdp.clickup.entity.ClickApp;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAuditorMapper.class})
public interface ClickAppMapper {
    ClickAppView mapToView(ClickApp clickApp);
}
