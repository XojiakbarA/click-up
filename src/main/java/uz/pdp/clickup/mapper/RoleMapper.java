package uz.pdp.clickup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.clickup.dto.request.RoleRequest;
import uz.pdp.clickup.dto.view.RoleView;
import uz.pdp.clickup.entity.Role;

@Component
public class RoleMapper {
    @Autowired
    private UserAuditorMapper userAuditorMapper;

    public RoleView mapToView(Role role) {
        if (role == null) return null;
        return RoleView.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .authorities(role.getAuthorities())
                    .createdAt(role.getCreatedAt())
                    .updatedAt(role.getUpdatedAt())
                    .createdBy(userAuditorMapper.mapToAuditorView(role.getCreatedBy()))
                    .updatedBy(userAuditorMapper.mapToAuditorView(role.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(Role role, RoleRequest request) {
        if (request.getName() != null && !request.getName().isBlank()) {
            role.setName(request.getName());
        }
    }
}
