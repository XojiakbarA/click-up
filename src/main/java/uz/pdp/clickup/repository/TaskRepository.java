package uz.pdp.clickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByNameAndListId(String name, Long listId);
    boolean existsByNameAndListIdAndIdNot(String name, Long listId, Long id);
    List<Task> findAllByListId(Long listId);
}
