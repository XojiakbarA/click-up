package uz.pdp.clickup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.clickup.dto.request.AuthoritiesRequest;
import uz.pdp.clickup.dto.request.RoleRequest;
import uz.pdp.clickup.dto.view.RoleView;
import uz.pdp.clickup.entity.Role;
import uz.pdp.clickup.enums.AuthorityType;

public interface RoleService {
    void create(String name, AuthorityType ...authorities);

    Role findByName(String name);

    Role findById(Long id);

    Page<RoleView> getAll(Pageable pageable);

    RoleView getById(Long id);

    RoleView create(RoleRequest request);

    RoleView update(RoleRequest request, Long id);

    void deleteById(Long id);

    RoleView addAuthorities(AuthoritiesRequest request, Long id);

    RoleView removeAuthorities(AuthoritiesRequest request, Long id);
}
