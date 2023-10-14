package uz.pdp.clickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByNameAndWorkspaceId(String name, Long workspaceId);
    boolean existsByNameAndWorkspaceIdAndIdNot(String name, Long workspaceId, Long id);
    List<Tag> findAllByWorkspaceId(Long workspaceId);
}
