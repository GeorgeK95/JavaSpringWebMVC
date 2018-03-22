package residentEvilApp.service.contacts;

import residentEvilApp.model.entity.Role;

/**
 * Created by George-Lenovo on 22/03/2018.
 */
public interface IRoleService {
    Role findFirstByAuthority(String user);
}
