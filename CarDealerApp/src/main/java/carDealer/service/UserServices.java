package carDealer.service;

import carDealer.model.User;
import carDealer.model.request.AddUserRequestModel;
import carDealer.repository.UserRepository;
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
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(AddUserRequestModel userRequestModel, RedirectAttributes model) {
        if (!userRequestModel.getPassword().equals(userRequestModel.getConfirmPassword())) {
            model.addFlashAttribute("user_registration_notification_false", "Invalid email, username or password mismatch.");
            return false;
        }

        User user = DTOConvertUtil.convert(userRequestModel, User.class);
        model.addFlashAttribute("user_registration_notification_true", "Successfully registered.");
        this.userRepository.saveAndFlush(user);

        return true;
    }
}
