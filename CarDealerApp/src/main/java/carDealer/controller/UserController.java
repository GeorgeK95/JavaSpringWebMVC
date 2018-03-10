package carDealer.controller;

import carDealer.model.request.AddUserRequestModel;
import carDealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(RedirectAttributes model, AddUserRequestModel userRequestModel) {
        if (!this.userService.register(userRequestModel, model)) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "base-layout";
    }


   /* @PostMapping("/login")
    public String login(HttpServletRequest request, AddUserRequestModel loginModel,
                        RedirectAttributes attributes) {
        User user = this.userServices.findByUsername(loginModel.getUsername());

        if (user == null || !user.getPassword().equals(loginModel.getPassword())) {
            attributes.addAttribute("login_failed", "Invalid username or password.");
            return "redirect:/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MODEL, loginModel);

        return "redirect:/cars/all";
    }*/

}
