package bg.galaxi.nuggets.controller;

import bg.galaxi.nuggets.repository.UserRepository;
import bg.galaxi.nuggets.service.UserService;
import bg.galaxi.nuggets.util.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Controller
public class PreferencesController extends BaseController {

    private final UserService userService;

    @Autowired
    public PreferencesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/preferences")
    public ModelAndView preferences() {
        return super.view(this.userService.getPreferences(), null);
    }
}
