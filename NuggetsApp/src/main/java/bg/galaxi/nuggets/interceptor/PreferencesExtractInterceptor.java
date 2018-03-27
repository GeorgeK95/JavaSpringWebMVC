package bg.galaxi.nuggets.interceptor;

import bg.galaxi.nuggets.model.response.NuggetResponseModel;
import bg.galaxi.nuggets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Component
public class PreferencesExtractInterceptor extends HandlerInterceptorAdapter {

    private static final String PREFERENCES_ERROR_MESSAGE = "Your preferences have not been loaded yet, or are not supported by our database.";

    private final UserService userService;

    @Autowired
    public PreferencesExtractInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!request.getRequestURL().toString().endsWith("/preferences")) {
            return;
        }

        Object model = modelAndView.getModelMap().get("model");
        modelAndView.getModelMap().addAttribute("model", PREFERENCES_ERROR_MESSAGE);
        if (model != null && ((List<NuggetResponseModel>) model).size() > 0) {
            modelAndView.getModelMap().addAttribute("model", (List<NuggetResponseModel>) model);
        }
    }
}
