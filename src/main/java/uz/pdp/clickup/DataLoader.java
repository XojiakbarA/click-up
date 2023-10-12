package uz.pdp.clickup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.clickup.enums.AuthorityType;
import uz.pdp.clickup.service.*;

import static uz.pdp.clickup.enums.RoleType.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ColorService colorService;

    @Override
    public void run(String... args) {
        roleService.create(ADMIN.name(), AuthorityType.values());
        roleService.create(MODERATOR.name());
        roleService.create(USER.name());

        colorService.create("white", "#ffffff");

        userService.create("First Admin", "admin@mail.com", "123", true, 1L, ADMIN.name());
        userService.create("First Moderator", "moderator@mail.com", "123", true, 1L, MODERATOR.name());
        userService.create("First User", "user@mail.com", "123", true, 1L, USER.name());
    }
    
}
