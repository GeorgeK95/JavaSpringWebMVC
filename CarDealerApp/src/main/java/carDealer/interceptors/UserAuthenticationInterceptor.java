package carDealer.interceptors;

import carDealer.annotations.PreAuthenticated;
import carDealer.model.request.UserRegisterRequestModel;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static carDealer.utils.Constants.LOGIN_MODEL;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethod().isAnnotationPresent(PreAuthenticated.class)) {
            boolean requirement = handlerMethod.getMethodAnnotation(PreAuthenticated.class).loggedInUserRequirement();
            UserRegisterRequestModel attribute = (UserRegisterRequestModel) request.getSession().getAttribute(LOGIN_MODEL);

            if (requirement) {
                if (attribute == null) {
                    response.sendRedirect("/login");
                    return true;
                }
            } else {
                if (attribute == null) {
                    return true;
                }
            }
        }

        return true;
    }
}
