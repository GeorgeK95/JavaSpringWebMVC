package carDealer.repository;

import carDealer.test.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
