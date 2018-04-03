package residentEvilApp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.ErrorListener;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
@RestController
public class GlobalExceptionController implements ErrorController {

    private static final String ERROR_URL = "/error";

    //    @ExceptionHandler(Exception.class)
    @RequestMapping(value = ERROR_URL)
    public ModelAndView getException() {
        ModelAndView modelAndView = new ModelAndView("base-layout");
        modelAndView.getModelMap().addAttribute("view", "error/error-404");
        modelAndView.getModelMap().addAttribute("pageTitle", "Resource Not Found");

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return ERROR_URL;
    }
}
