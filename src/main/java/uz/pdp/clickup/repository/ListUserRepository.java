package uz.pdp.clickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.clickup.entity.ListUser;

@Repository
public interface ListUserRepository extends JpaRepository<ListUser, Long> {
    List<ListUser> findAllByListId(Long listId);

    boolean existsByListIdAndPersonId(Long listId, Long personId);
}
