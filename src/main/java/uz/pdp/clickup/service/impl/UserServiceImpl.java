package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.UserRequest;
import uz.pdp.clickup.dto.view.UserView;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.UserMapper;
import uz.pdp.clickup.repository.UserRepository;
import uz.pdp.clickup.service.ColorService;
import uz.pdp.clickup.service.RoleService;
import uz.pdp.clickup.service.UserService;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final String resourceName = User.class.getSimpleName();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private UserMapper userMapper;

    private void checkToExistsByUsername(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceExistsException(resourceName, "email", email);
        }
    }
    private void checkToExistsByUsername(String email, Long id) {
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            throw new ResourceExistsException(resourceName, "email", email);
        }
    }
    private void setAttributes(UserRequest request, User user) {
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

    @Override
    public Page<UserView> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::mapToView);
    }

    @Override
    public UserView getById(Long id) {
        return userMapper.mapToView(findById(id));
    }

    @Override
    public UserView create(UserRequest request) {
        checkToExistsByUsername(request.getEmail());

        User user = new User();

        setAttributes(request, user);

        return userMapper.mapToView(save(user));
    }

    @Override
    public User create(String fullName, String email, String password, Boolean enabled, Long colorId, String roleName) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceExistsException(resourceName, "email", email);
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName(roleName));
        user.setEnabled(enabled);
        if (colorId != null) {
            user.setColor(colorService.findById(colorId));
        }

        Random random = new Random();
        String verifyCode = String.format("%04d", random.nextInt(10000));

        user.setVerifyCode(verifyCode);

        return save(user);
    }

    @Override
    public UserView update(UserRequest request, Long id) {
        checkToExistsByUsername(request.getEmail(), id);

        User user = findById(id);

        setAttributes(request, user);

        return userMapper.mapToView(save(user));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "email", email)
        );
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void lock(Long id) {
        User user = findById(id);

        user.setAccountNonLocked(false);

        save(user);
    }

    @Override
    public void unlock(Long id) {
        User user = findById(id);

        user.setAccountNonLocked(true);

        save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        userRepository.deleteById(id);
    }

}
