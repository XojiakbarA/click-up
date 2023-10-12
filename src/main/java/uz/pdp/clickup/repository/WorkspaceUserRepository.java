package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.WorkspaceUser;

import java.util.List;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, Long> {
    boolean existsByWorkspaceIdAndPersonId(Long workspaceId, Long personId);
    List<WorkspaceUser> findAllByWorkspaceRoleNameAndWorkspaceId(String workspaceRoleName, Long workspaceId);
}
