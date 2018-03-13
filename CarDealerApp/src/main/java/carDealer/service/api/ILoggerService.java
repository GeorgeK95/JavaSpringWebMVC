package carDealer.service.api;

import carDealer.model.response.LoggerResponseModel;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface ILoggerService {
    static ModelAndView constructModelAndView(String redirectTo,
                                              String username,
                                              String operation,
                                              String table) {
        ModelAndView modelAndView = new ModelAndView(redirectTo);

        modelAndView.addObject("user", username);
        modelAndView.addObject("operation", operation);
        modelAndView.addObject("modifiedTable", table);

        return modelAndView;
    }

    void addLog(ModelMap modelMap);

    List<LoggerResponseModel> findAll();

    void removeAll();

    List<LoggerResponseModel> search(String search, String username) throws NullPointerException;
}
