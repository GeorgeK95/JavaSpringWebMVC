package residentEvilApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.model.entity.Role;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.repository.RoleRepository;
import residentEvilApp.repository.UserRepository;
import residentEvilApp.service.contacts.IUserService;
import residentEvilApp.util.DTOConverter;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserRegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);

        user.addRole(this.roleRepository.findFirstByName("USER"));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }

    @Override
    public Role getUserRole(String username) {
        return this.roleRepository.findFirstByName(
                this.userRepository
                        .findFirstByUsername(username)
                        .getRoles()
                        .stream()
                        .findFirst().get().getName()
        );
    }
}
