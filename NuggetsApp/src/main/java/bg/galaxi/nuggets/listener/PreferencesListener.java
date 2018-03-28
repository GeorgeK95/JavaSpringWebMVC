package bg.galaxi.nuggets.listener;

import bg.galaxi.nuggets.model.entity.Nugget;
import bg.galaxi.nuggets.model.entity.User;
import bg.galaxi.nuggets.model.pojo.NewUserPOJO;
import bg.galaxi.nuggets.repository.UserRepository;
import bg.galaxi.nuggets.service.NuggetService;
import bg.galaxi.nuggets.util.IAuthenticationFacade;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Component
public class PreferencesListener {

    private final IAuthenticationFacade authenticationFacade;

    private final UserRepository userRepository;
    private final NuggetService nuggetService;

    @Autowired
    public PreferencesListener(IAuthenticationFacade authenticationFacade, UserRepository userRepository, NuggetService nuggetService) {
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.nuggetService = nuggetService;
    }

    @JmsListener(destination = "extracted_preferences")
    public void userRegisteredListener(Message<String> preferences) {
        NewUserPOJO newUserPOJO = new Gson().fromJson(preferences.getPayload(), NewUserPOJO.class);
        System.out.println(newUserPOJO.getIds());
        User user = this.userRepository.findFirstByUsername(newUserPOJO.getUsername());
        System.out.println(user);
        System.out.println("idssss:"+newUserPOJO.getIds());
        List<Nugget> nuggets = this.nuggetService.findAllById(newUserPOJO.getIds().toArray(new String[0]));
        user.setPreferences(nuggets);
        System.out.println(user);
//        userRepository.saveAndFlush(user);
    }
}
