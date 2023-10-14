package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.Icon;

@Repository
public interface IconRepository extends JpaRepository<Icon, Long> {

}
