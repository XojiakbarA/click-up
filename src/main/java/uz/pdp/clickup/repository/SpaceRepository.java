package uz.pdp.clickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByNameAndWorkspaceId(String name, Long workspaceId);
    boolean existsByNameAndWorkspaceIdAndIdNot(String name, Long workspaceId, Long id);
    List<Space> findAllByWorkspaceId(Long workspaceId);
}
