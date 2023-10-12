package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByHexCode(String hexCode);
    boolean existsByHexCodeAndIdNot(String hexCode, Long id);
}
