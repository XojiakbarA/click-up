package uz.pdp.clickup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.clickup.dto.request.UserRequest;
import uz.pdp.clickup.dto.view.UserView;
import uz.pdp.clickup.entity.User;

public interface UserService {
    User create(String fullName, String email, String password, Long colorId, String roleName);

    Page<UserView> getAll(Pageable pageable);

    UserView getById(Long id);

    UserView create(UserRequest request);

    UserView update(UserRequest request, Long id);

    User findByEmail(String email);

    User save(User user);

    void lock(Long id);

    void unlock(Long id);

    void deleteById(Long id);
}
