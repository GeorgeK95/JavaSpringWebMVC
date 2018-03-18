package residentEvilApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentEvilApp.model.entity.Role;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByName(String name);
}
