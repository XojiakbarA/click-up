package uz.pdp.clickup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.clickup.dto.request.UserRequest;
import uz.pdp.clickup.dto.view.UserView;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.RoleService;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserAuditorMapper userAuditorMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ColorService colorService;

    public UserView mapToView(User user) {
        if (user == null) return null;
        return UserView.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .role(roleMapper.mapToView(user.getRole()))
                    .accountNonLocked(user.getAccountNonLocked())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .createdBy(userAuditorMapper.mapToAuditorView(user.getCreatedBy()))
                    .updatedBy(userAuditorMapper.mapToAuditorView(user.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(User user, UserRequest request) {
        if (request.getRoleId() != null) {
            user.setRole(roleService.findById(request.getRoleId()));
        }
        if (request.getColorId() != null) {
            user.setColor(colorService.findById(request.getColorId()));
        }
        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName());
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }
}
