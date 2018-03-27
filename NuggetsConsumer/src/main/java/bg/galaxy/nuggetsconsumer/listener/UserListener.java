package bg.galaxy.nuggetsconsumer.listener;

import bg.galaxy.nuggetsconsumer.model.pojo.NewUserPOJO;
import bg.galaxy.nuggetsconsumer.repository.NuggetRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Component
public class UserListener {

    private final NuggetRepository nuggetRepository;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserListener(NuggetRepository nuggetRepository, JmsTemplate jmsTemplate) {
        this.nuggetRepository = nuggetRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "new_user")
    public void userRegisteredListener(Message<String> preferences) {
        NewUserPOJO newUserPOJO = new Gson().fromJson(preferences.getPayload(), NewUserPOJO.class);
        List<String> nuggetsIds = this.nuggetRepository.
                findByName(newUserPOJO.getUsername().replace(",", "|"));
        this.jmsTemplate.convertAndSend("extracted_preferences", preferences.getPayload());
    }

}
