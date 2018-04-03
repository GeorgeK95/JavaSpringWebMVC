package residentEvilApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.exception.UserNotFoundException;
import residentEvilApp.model.entity.Role;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserDeleteRequestModel;
import residentEvilApp.model.request.UserEditRequestModel;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.model.response.UserResponseModel;
import residentEvilApp.repository.RoleRepository;
import residentEvilApp.repository.UserRepository;
import residentEvilApp.service.contacts.IRoleService;
import residentEvilApp.service.contacts.IUserService;
import residentEvilApp.util.DTOConverter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IRoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, IRoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);

        user.setPassword(this.passwordEncoder.encode(requestModel.getPassword()));

        user.addRole(this.roleService.findFirstByAuthority("USER"));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = this.userRepository.findFirstByUsername(username);

        if (user == null) throw new UserNotFoundException();

        return user;
    }

    @Override
    public Role getUserRole(String username) {
        return this.roleService.findFirstByAuthority(
                this.userRepository
                        .findFirstByUsername(username)
                        .getAuthorities()
                        .stream()
                        .findFirst().get().getAuthority()
        );
    }

    @Override
    public List<UserResponseModel> findAll() {
        return DTOConverter.convert(this.userRepository.findAll(), UserResponseModel.class);
    }

    @Override
    public UserDeleteRequestModel findOne(Long id) {
        return DTOConverter.convert(this.userRepository.findById(id).get(), UserDeleteRequestModel.class);
    }

    @Override
    public void deleteUser(Long id) {
        try {
            User user = this.userRepository.findById(id).get();
            this.userRepository.delete(user);
        } catch (NoSuchElementException nsee) {
            throw new UserNotFoundException();
        }

    }

    @Override
    public void editUser(Long id, UserEditRequestModel userModel) {
        try {
            User user = this.userRepository.findById(id).get();

            user.setUsername(userModel.getUsername());
            user.setPassword(this.passwordEncoder.encode(userModel.getPassword()));

            if (userModel.getModerator()) user.addRole(this.roleService.findFirstByAuthority("ROLE_MODERATOR"));
            if (userModel.getUser()) user.addRole(this.roleService.findFirstByAuthority("ROLE_USER"));
            if (userModel.getAdmin()) user.addRole(this.roleService.findFirstByAuthority("ROLE_ADMIN"));

            this.userRepository.saveAndFlush(user);
        } catch (NoSuchElementException nsee) {
            throw new UserNotFoundException();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(((GrantedAuthority) role).getAuthority()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }
}

