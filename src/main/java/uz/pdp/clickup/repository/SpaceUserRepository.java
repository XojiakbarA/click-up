package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickup.entity.SpaceUser;

import java.util.List;

@Repository
public interface SpaceUserRepository extends JpaRepository<SpaceUser, Long> {
    boolean existsBySpaceIdAndPersonId(Long spaceId, Long personId);
    List<SpaceUser> findAllBySpaceId(Long spaceId);
}
