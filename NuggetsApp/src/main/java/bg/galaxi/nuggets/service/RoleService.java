package bg.galaxi.nuggets.service;

import bg.galaxi.nuggets.model.entity.Role;
import bg.galaxi.nuggets.repository.RoleRepository;
import bg.galaxi.nuggets.service.contacts.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by George-Lenovo on 22/03/2018.
 */
@Service
@Transactional
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findFirstByAuthority(String user) {
        return this.roleRepository.findFirstByAuthority(user);
    }
}
