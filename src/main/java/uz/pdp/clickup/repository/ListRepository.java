package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.List;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
    boolean existsByNameAndFolderId(String name, Long folderId);
    boolean existsByNameAndFolderIdAndIdNot(String name, Long folderId, Long id);
    java.util.List<List> findAllByFolderId(Long folderId);
}
