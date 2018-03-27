package bg.galaxi.nuggets.loader;

import bg.galaxi.nuggets.model.entity.Role;
import bg.galaxi.nuggets.model.entity.User;
import bg.galaxi.nuggets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
        User admin = new User("admin", this.passwordEncoder.encode("admin"));
        User user = new User("user", this.passwordEncoder.encode("user"));

        admin.addRole(new Role("ROLE_ADMIN"));
        user.addRole(new Role("ROLE_USER"));

        this.userRepository.saveAll(Set.of(admin, user));
    }
}