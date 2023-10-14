package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.TaskAttachment;

@Repository
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

}
