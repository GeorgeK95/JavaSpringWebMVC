package residentEvilApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.getModelMap().addAttribute("view", "error/unauthorized");
        modelAndView.getModelMap().addAttribute("pageTitle", "Unauthorized");

        return modelAndView;
    }

}
