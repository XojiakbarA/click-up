package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.WorkspaceRole;

import java.util.List;

@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, Long> {
    boolean existsByNameAndWorkspaceId(String name, Long workspaceId);
    boolean existsByNameAndWorkspaceIdAndIdNot(String name, Long workspaceId, Long id);
    List<WorkspaceRole> findAllByWorkspaceId(Long workspaceId);
}
