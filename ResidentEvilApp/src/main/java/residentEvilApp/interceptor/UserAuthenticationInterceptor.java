package residentEvilApp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import residentEvilApp.annotation.PreAuthenticated;
import residentEvilApp.model.entity.Role;
import residentEvilApp.model.request.UserLoginRequestModel;
import residentEvilApp.model.request.UserRegisterRequestModel;
import residentEvilApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;

    @Autowired
    public UserAuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            if (handlerMethod.getMethod().isAnnotationPresent(PreAuthenticated.class)) {
                boolean requirement = handlerMethod.getMethodAnnotation(PreAuthenticated.class).loggedIn();
                UserLoginRequestModel userModel = (UserLoginRequestModel) request.getSession().getAttribute("userModel");

                if (requirement) {
                    if (userModel == null) {
                        response.sendRedirect("/login");
                        return true;
                    }

                    String roleName = handlerMethod.getMethodAnnotation(PreAuthenticated.class).inRole();
                    Role userRole = this.userService.getUserRole(userModel.getUsername());

                    if (roleName.equals("ADMIN") && userRole.getAuthority().equals("USER")) {
                        response.sendRedirect("/login");
                    }

                }
            }
        } catch (ClassCastException ignored) {

        }

        return true;
    }
}
