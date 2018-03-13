package carDealer.service.api;

import carDealer.model.entity.User;
import carDealer.model.request.UserRegisterRequestModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface IUserService {
    boolean register(UserRegisterRequestModel userRequestModel, RedirectAttributes model);

    User findByEmail(String email);

    User findByUsername(String username);
}
