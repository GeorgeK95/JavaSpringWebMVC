package residentEvilApp.service.contacts;

import residentEvilApp.model.entity.Role;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserRegisterRequestModel;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
public interface IUserService {
    void register(UserRegisterRequestModel requestModel);

    User findByUsername(String username);

    Role getUserRole(String username);
}
