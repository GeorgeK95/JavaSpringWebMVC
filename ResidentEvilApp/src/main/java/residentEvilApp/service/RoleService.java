package residentEvilApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.model.entity.Role;
import residentEvilApp.repository.RoleRepository;
import residentEvilApp.service.contacts.IRoleService;

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
