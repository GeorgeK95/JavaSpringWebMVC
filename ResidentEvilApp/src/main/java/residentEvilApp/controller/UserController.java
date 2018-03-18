package residentEvilApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import residentEvilApp.annotation.PreAuthenticated;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserLoginRequestModel;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by George-Lenovo on 18/03/2018.
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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        this.userService.register(requestModel);

        attributes.addFlashAttribute("user_registration_notification_true", String.format("Successfully registered user with name %s.",
                requestModel.getUsername()));

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String registerProcess(RedirectAttributes attributes, UserLoginRequestModel requestModel, HttpServletRequest request) {
        User user = this.userService.findByUsername(requestModel.getUsername());

        if (user == null || !user.getPassword().equals(requestModel.getPassword())) {
            attributes.addFlashAttribute("login_failed", "Invalid username or password.");
            return "redirect:/login";
        }

        request.getSession().setAttribute("userModel", requestModel);
        attributes.addFlashAttribute("login_successful", "You have logged in successfully.");

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutProcess(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }

    /*TEST PAGES*/
    @GetMapping("/anonymous")
    @ResponseBody
    public String anonymous() {
        return "This page is visible by guests, users, admins";
    }

    @GetMapping("/user")
    @ResponseBody
    @PreAuthenticated(loggedIn = true)
    public String user() {
        return "This page is visible by users, admins";
    }

    @GetMapping("/admin")
    @ResponseBody
    @PreAuthenticated(loggedIn = true, inRole = "ADMIN")
    public String admin() {
        return "This page is visible by admins";
    }
}
