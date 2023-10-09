package uz.pdp.clickup.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.clickup.dto.view.UserAuditorView;
import uz.pdp.clickup.entity.User;

@Component
public class UserAuditorMapper {
    public UserAuditorView mapToAuditorView(User user) {
        if (user == null) return null;
        return UserAuditorView.builder()
                    .id(user.getId())
                    .email(user.getUsername())
                    .roleName(user.getRole().getName())
                    .build();
    }
}
