package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    
}
