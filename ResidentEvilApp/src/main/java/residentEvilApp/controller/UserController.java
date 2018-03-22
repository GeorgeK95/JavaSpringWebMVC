package residentEvilApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import residentEvilApp.annotation.PreAuthenticated;
import residentEvilApp.model.entity.User;
import residentEvilApp.model.request.UserDeleteRequestModel;
import residentEvilApp.model.request.UserEditRequestModel;
import residentEvilApp.model.request.UserLoginRequestModel;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.model.response.UserResponseModel;
import residentEvilApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/register")
    public String registerProcess(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        this.userService.register(requestModel);

        attributes.addFlashAttribute("user_registration_notification_true", String.format("Successfully registered user with name %s.",
                requestModel.getUsername()));

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("login_failed", "Invalid username or password.");
        }

        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping("/users/all")
    @PreAuthorize("isAuthenticated() AND hasRole('ROLE_ADMIN')")
    public String allUsers(Model model) {
        model.addAttribute("view", "user/all-users");
        model.addAttribute("users", this.userService.findAll());

        return "base-layout";
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("isAuthenticated() AND hasRole('ROLE_ADMIN')")
    public String editUser(Model model, @PathVariable Long id, @ModelAttribute UserEditRequestModel userRequestModel) {
        model.addAttribute("view", "user/edit");

        return "base-layout";
    }

    @PostMapping("/users/edit/{id}")
    @PreAuthorize("isAuthenticated() AND hasRole('ROLE_ADMIN')")
    public String editUserProcess(@PathVariable Long id, UserEditRequestModel userModel) {
        this.userService.editUser(id, userModel);

        return "redirect:/users/all";
    }

    @GetMapping("/users/delete/{id}")
    @PreAuthorize("isAuthenticated() AND hasRole('ROLE_ADMIN')")
    public String deleteUser(Model model, @PathVariable Long id) {
        model.addAttribute("view", "user/delete");
        model.addAttribute("userModel", this.userService.findOne(id));

        return "base-layout";
    }

    @PostMapping("/users/delete/{id}")
    @PreAuthorize("isAuthenticated() AND hasRole('ROLE_ADMIN')")
    public String deleteUserProcess(@PathVariable Long id) {
        this.userService.deleteUser(id);

        return "redirect:/users/all";
    }
}
