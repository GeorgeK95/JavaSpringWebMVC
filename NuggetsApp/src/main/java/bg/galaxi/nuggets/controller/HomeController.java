package bg.galaxi.nuggets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView home() {
        return super.view();
    }
}
