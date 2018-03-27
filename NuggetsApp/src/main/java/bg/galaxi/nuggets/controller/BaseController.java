package bg.galaxi.nuggets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
public abstract class BaseController {

    ModelAndView view() {
        return this.view(null, "base-layout");
    }

    ModelAndView redirect(String redirectUrl) {
        return this.view(null, "redirect:/".concat(redirectUrl));
    }

    private ModelAndView view(Object viewModel, String layout) {
        StackTraceElement[] stackTraceElements = java.lang.Thread.currentThread().getStackTrace();
        StackTraceElement callee = stackTraceElements[3];
        String[] names = callee.getClassName().split("\\.");
        String folder = names[names.length - 1].replace("Controller", "").toLowerCase();
        String file = callee.getMethodName();

        return this.view(folder + "/" + file, viewModel, layout);
    }

    protected ModelAndView view(Object viewModel, Map<String, Object> attributes) {
        ModelAndView modelAndView = this.view(viewModel, "base-layout");
        if (attributes != null) attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }

    private ModelAndView view(String viewName, Object viewModel, String layout) {
        ModelAndView modelAndView = new ModelAndView(layout);
        modelAndView.getModelMap().addAttribute("view", viewName);

        if (viewModel != null) modelAndView.getModelMap().addAttribute("model", viewModel);

        return modelAndView;
    }

}
