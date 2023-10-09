package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
