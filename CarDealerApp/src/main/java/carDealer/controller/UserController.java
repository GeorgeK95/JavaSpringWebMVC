package carDealer.controller;

import carDealer.annotations.PreAuthenticated;
import carDealer.model.entity.User;
import carDealer.model.request.UserRegisterRequestModel;
import carDealer.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static carDealer.utils.Constants.LOGIN_MODEL;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@Controller
public class UserController {

    private final IUserService IUserService;
    private final IUserService IUserServices;

    @Autowired
    public UserController(IUserService IUserService, IUserService IUserServices) {
        this.IUserService = IUserService;
        this.IUserServices = IUserServices;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(RedirectAttributes model, UserRegisterRequestModel userRequestModel) {
        if (!this.IUserService.register(userRequestModel, model)) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, UserRegisterRequestModel loginModel,
                        RedirectAttributes attributes) {
        User user = this.IUserServices.findByUsername(loginModel.getUsername());

        if (user == null || !user.getPassword().equals(loginModel.getPassword())) {
            attributes.addFlashAttribute("login_failed", "Invalid username or password.");
            return "redirect:/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MODEL, loginModel);

        return "redirect:/cars/all";
    }

    @PreAuthenticated(loggedInUserRequirement = true)
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
