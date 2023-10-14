package uz.pdp.clickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.TaskUser;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {
    boolean existsByTaskIdAndPersonId(Long taskId, Long personId);
    List<TaskUser> findAllByTaskId(Long taskId);
}
