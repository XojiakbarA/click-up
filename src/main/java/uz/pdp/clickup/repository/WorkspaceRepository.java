package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.Workspace;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    boolean existsByNameAndOwnerId(String name, Long ownerId);
    boolean existsByNameAndOwnerIdAndIdNot(String name, Long ownerId, Long id);
    List<Workspace> findAllByOwnerId(Long ownerId);
}
