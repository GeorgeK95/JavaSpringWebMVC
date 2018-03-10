package carDealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by George-Lenovo on 03/08/2018.
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("view", "home/index");

        return "base-layout";
    }
}
