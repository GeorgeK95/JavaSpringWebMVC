package carDealer.service;

import carDealer.model.entity.User;
import carDealer.model.request.AddUserRequestModel;
import carDealer.repository.RoleRepository;
import carDealer.repository.UserRepository;
import carDealer.test.Role;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@Service
@Transactional
public class UserServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServices(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean register(AddUserRequestModel userRequestModel, RedirectAttributes model) {

        if (!userRequestModel.getPassword().equals(userRequestModel.getConfirmPassword())) {
            model.addFlashAttribute("user_registration_notification_false", "Invalid email, username or password mismatch.");
            return false;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = DTOConvertUtil.convert(userRequestModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));

        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.addRole(userRole);

        model.addFlashAttribute("user_registration_notification_true", "Successfully registered.");
        this.userRepository.saveAndFlush(user);

        return true;
    }

    public User findByUsername(String username) {
        return DTOConvertUtil.convert(this.userRepository.findByUsername(username), User.class);
    }
}
