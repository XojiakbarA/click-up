package uz.pdp.clickup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.View;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    Optional<View> findByName(String name);
}
