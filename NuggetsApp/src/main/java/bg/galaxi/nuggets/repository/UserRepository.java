package bg.galaxi.nuggets.repository;

import bg.galaxi.nuggets.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
}
