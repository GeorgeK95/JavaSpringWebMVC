package carDealer.interceptors;

import carDealer.annotations.LoggedAction;
import carDealer.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final LoggerService loggerService;

    @Autowired
    public LoggerInterceptor(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.getMethod().isAnnotationPresent(LoggedAction.class)) {
            return;
        }

        ModelMap modelMap = modelAndView.getModelMap();

        this.loggerService.addLog(modelMap);
    }
}
