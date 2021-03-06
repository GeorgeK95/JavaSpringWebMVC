package carDealer.repository;

import carDealer.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
