package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.Folder;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    boolean existsByNameAndSpaceId(String name, Long spaceId);
    boolean existsByNameAndSpaceIdAndIdNot(String name, Long spaceId, Long id);
    List<Folder> findAllBySpaceId(Long spaceId);
}
