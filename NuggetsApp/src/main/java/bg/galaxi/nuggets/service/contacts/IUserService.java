package bg.galaxi.nuggets.service.contacts;

import bg.galaxi.nuggets.model.entity.Role;
import bg.galaxi.nuggets.model.entity.User;
import bg.galaxi.nuggets.model.request.UserRegisterRequestModel;
import bg.galaxi.nuggets.model.response.NuggetResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
public interface IUserService extends UserDetailsService {
    boolean register(UserRegisterRequestModel requestModel, RedirectAttributes attributes);

    User findByUsername(String username);

    Role getUserRole(String username);

    List<NuggetResponseModel> getPreferences();
}
