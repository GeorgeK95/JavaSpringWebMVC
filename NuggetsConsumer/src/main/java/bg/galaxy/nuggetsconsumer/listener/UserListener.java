package bg.galaxy.nuggetsconsumer.listener;

import bg.galaxy.nuggetsconsumer.model.pojo.NewUserPOJO;
import bg.galaxy.nuggetsconsumer.repository.NuggetRepository;
import bg.galaxy.nuggetsconsumer.service.INuggetService;
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

    private final JmsTemplate jmsTemplate;

    private final INuggetService nuggetService;

    @Autowired
    public UserListener( JmsTemplate jmsTemplate, INuggetService nuggetService) {
        this.jmsTemplate = jmsTemplate;
        this.nuggetService = nuggetService;
    }

    @JmsListener(destination = "new_user")
    public void userRegisteredListener(Message<String> preferences) {
        NewUserPOJO newUserPOJO = new Gson().fromJson(preferences.getPayload(), NewUserPOJO.class);
        List<String> nuggetsIds =this.nuggetService.findByName(newUserPOJO.getUsername());
        newUserPOJO.setIds(nuggetsIds);
        this.jmsTemplate.convertAndSend("extracted_preferences", new Gson().toJson(newUserPOJO));
    }

}
