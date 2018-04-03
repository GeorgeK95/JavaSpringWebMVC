package residentEvilApp.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import residentEvilApp.model.entity.Role;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.entity.Virus;
import residentEvilApp.repository.UserRepository;

import java.util.Set;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        addUsersAndRoles();
    }

    private void addUsersAndRoles() {
        User admin = new User("admin@admin.com", "admin", this.passwordEncoder.encode("admin"));
        User user = new User("user@abv.bg", "user", this.passwordEncoder.encode("user"));
        User moderator = new User("moderator@abv.bg", "moderator", this.passwordEncoder.encode("moderator"));

        admin.addRole(new Role("ROLE_ADMIN"));
        user.addRole(new Role("ROLE_USER"));
        moderator.addRole(new Role("ROLE_MODERATOR"));

        this.userRepository.saveAll(Set.of(admin, user, moderator));
    }
}