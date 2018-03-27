package bg.galaxi.nuggets.service;

import bg.galaxi.nuggets.model.entity.Nugget;
import bg.galaxi.nuggets.model.entity.Role;
import bg.galaxi.nuggets.model.entity.User;
import bg.galaxi.nuggets.model.pojo.NewUserPOJO;
import bg.galaxi.nuggets.model.request.UserRegisterRequestModel;
import bg.galaxi.nuggets.model.response.NuggetResponseModel;
import bg.galaxi.nuggets.repository.UserRepository;
import bg.galaxi.nuggets.service.contacts.IRoleService;
import bg.galaxi.nuggets.service.contacts.IUserService;
import bg.galaxi.nuggets.util.AuthenticationFacade;
import bg.galaxi.nuggets.util.DTOConverter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final IRoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JmsTemplate jmsTemplate;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public UserService(UserRepository userRepository, IRoleService roleService, BCryptPasswordEncoder passwordEncoder, JmsTemplate jmsTemplate, AuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jmsTemplate = jmsTemplate;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public boolean register(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirmPassword())) {
            attributes.addFlashAttribute("password_mismatch", "Password mismatch.");
            return false;
        }

        User user = DTOConverter.convert(requestModel, User.class);

        user.setPassword(this.passwordEncoder.encode(requestModel.getPassword()));

        user.addRole(this.roleService.findFirstByAuthority("USER"));

        NewUserPOJO newUserPOJO = new NewUserPOJO(
                user.getUsername(),
                requestModel.getPreferences()
        );

        this.jmsTemplate.convertAndSend("new_user", new Gson().toJson(newUserPOJO));

        this.userRepository.saveAndFlush(user);

        return true;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }

    @Override
    public Role getUserRole(String username) {
        return this.roleService.findFirstByAuthority(
                this.userRepository
                        .findFirstByUsername(username)
                        .getAuthorities()
                        .stream()
                        .findFirst().get().getAuthority()
        );
    }

    @Override
    public List<NuggetResponseModel> getPreferences() {
        String name = authenticationFacade.getAuthentication().getName();
        if (name.equals("anonymousUser")) {
            return null;
        }
        User user = this.userRepository.findFirstByUsername(name);
        List<Nugget> preferences = user.getPreferences();
        List<NuggetResponseModel> responseModels = DTOConverter.convert(preferences, NuggetResponseModel.class);
        return responseModels;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(((GrantedAuthority) role).getAuthority()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
    }
}

