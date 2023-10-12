package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.FolderUser;

import java.util.List;

@Repository
public interface FolderUserRepository extends JpaRepository<FolderUser, Long> {
    boolean existsByFolderIdAndPersonId(Long folderId, Long personId);
    List<FolderUser> findAllByFolderId(Long folderId);
}
