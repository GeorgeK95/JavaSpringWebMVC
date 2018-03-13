package carDealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static carDealer.utils.Constants.LOGIN_MODEL;

/**
 * Created by George-Lenovo on 03/08/2018.
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String home(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute(LOGIN_MODEL) != null) {
            model.addAttribute(LOGIN_MODEL, LOGIN_MODEL);
        }

        model.addAttribute("view", "home/index");

        return "base-layout";
    }
}
