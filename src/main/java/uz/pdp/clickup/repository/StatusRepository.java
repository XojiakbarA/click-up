package uz.pdp.clickup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsByNameAndListId(String name, Long listId);
    boolean existsByNameAndListIdAndIdNot(String name, Long listId, Long id);
    List<Status> findAllByListId(Long listId);
    Optional<Status> findByNameAndListId(String name, Long listId);
}
