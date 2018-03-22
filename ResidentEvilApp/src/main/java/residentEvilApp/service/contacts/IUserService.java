package residentEvilApp.service.contacts;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import residentEvilApp.model.entity.Role;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserDeleteRequestModel;
import residentEvilApp.model.request.UserEditRequestModel;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.model.response.UserResponseModel;

import java.util.List;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
public interface IUserService extends UserDetailsService {
    void register(UserRegisterRequestModel requestModel);

    User findByUsername(String username);

    Role getUserRole(String username);

    List<UserResponseModel> findAll();

    UserDeleteRequestModel findOne(Long id);

    void deleteUser(Long id);

    void editUser(Long id, UserEditRequestModel userModel);
}
