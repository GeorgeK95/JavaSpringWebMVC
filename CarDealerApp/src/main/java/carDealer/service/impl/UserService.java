package carDealer.service.impl;

import carDealer.model.entity.User;
import carDealer.model.request.UserRegisterRequestModel;
import carDealer.repository.RoleRepository;
import carDealer.repository.UserRepository;
import carDealer.model.entity.Role;
import carDealer.service.api.IUserService;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by George-Lenovo on 03/04/2018.
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
    public boolean register(UserRegisterRequestModel userRequestModel, RedirectAttributes model) {

        if (!userRequestModel.getPassword().equals(userRequestModel.getConfirmPassword())) {
            model.addFlashAttribute("user_registration_notification_false", "Invalid email, username or password mismatch.");
            return false;
        }

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = DTOConvertUtil.convert(userRequestModel, User.class);
//        user.setPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));

        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.addRole(userRole);

        model.addFlashAttribute("user_registration_notification_true", "Successfully registered.");
        this.userRepository.saveAndFlush(user);

        return true;
    }

    @Override
    public User findByEmail(String email) {
        User byEmail = this.userRepository.findByEmail(email);
        return byEmail != null ? DTOConvertUtil.convert(byEmail, User.class) : null;
    }

    @Override
    public User findByUsername(String username) {
        User byUsername = this.userRepository.findByUsername(username);
        return byUsername != null ? DTOConvertUtil.convert(byUsername, User.class) : null;
    }
}
