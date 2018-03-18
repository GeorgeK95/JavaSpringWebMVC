package residentEvilApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentEvilApp.model.entity.User;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);
}
