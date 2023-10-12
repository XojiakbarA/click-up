package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.request.AuthoritiesRequest;
import uz.pdp.clickup.dto.request.RoleRequest;
import uz.pdp.clickup.dto.view.RoleView;
import uz.pdp.clickup.entity.Role;
import uz.pdp.clickup.enums.AuthorityType;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.RoleMapper;
import uz.pdp.clickup.repository.RoleRepository;
import uz.pdp.clickup.service.RoleService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final String resourceName = Role.class.getSimpleName();

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    private Role save(Role role) {
        return roleRepository.save(role);
    }
    private void checkToExistsByName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkToExistsByName(String name, Long id) {
        if (roleRepository.existsByNameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private Set<AuthorityType> mapStringsToAuthorities(Set<String> strings) {
        return strings.stream().map(a -> AuthorityType.valueOf(a.toUpperCase())).collect(Collectors.toSet());
    }
    private void setAttributes(RoleRequest request, Role role) {
        if (request.getName() != null && !request.getName().isBlank()) {
            role.setName(request.getName());
        }
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "name", name)
        );
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public void create(String name, AuthorityType... authorities) {
        checkToExistsByName(name);

        Role role = new Role();

        role.setName(name);
        role.setAuthorities(Set.of(authorities));

        save(role);
    }

    @Override
    public Page<RoleView> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::mapToView);
    }

    @Override
    public RoleView getById(Long id) {
        return roleMapper.mapToView(findById(id));
    }
    @Override
    public RoleView create(RoleRequest request) {
        checkToExistsByName(request.getName());

        Role role = new Role();

        setAttributes(request, role);

        return roleMapper.mapToView(save(role));
    }

    @Override
    public RoleView update(RoleRequest request, Long id) {
        checkToExistsByName(request.getName(), id);

        Role role = findById(id);

        setAttributes(request, role);

        return roleMapper.mapToView(save(role));
    }

    @Override
    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        roleRepository.deleteById(id);
    }
    @Override
    public RoleView addAuthorities(AuthoritiesRequest request, Long id) {
        Role role = findById(id);

        Set<AuthorityType> authorities = mapStringsToAuthorities(request.getAuthorities());

        role.getAuthorities().addAll(authorities);

        return roleMapper.mapToView(role);
    }
    @Override
    public RoleView removeAuthorities(AuthoritiesRequest request, Long id) {
        Role role = findById(id);

        Set<AuthorityType> authorities = mapStringsToAuthorities(request.getAuthorities());

        role.getAuthorities().removeAll(authorities);

        return roleMapper.mapToView(role);
    }
}
