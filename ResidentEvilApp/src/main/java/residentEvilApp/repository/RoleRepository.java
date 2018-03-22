package residentEvilApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentEvilApp.model.entity.Role;

import java.util.List;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByAuthority(String name);

}
