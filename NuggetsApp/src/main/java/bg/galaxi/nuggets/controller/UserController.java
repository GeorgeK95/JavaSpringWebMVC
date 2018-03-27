package bg.galaxi.nuggets.controller;

import bg.galaxi.nuggets.model.request.UserRegisterRequestModel;
import bg.galaxi.nuggets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view();
    }

    @PostMapping("/register")
    public ModelAndView registerProcess(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!this.userService.register(requestModel, attributes)) {
            return super.redirect("register");
        }

        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

}
