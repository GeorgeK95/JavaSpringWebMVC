package carDealer.controller;

import carDealer.model.entity.Logger;
import carDealer.model.response.LoggerResponseModel;
import carDealer.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
@Controller
@RequestMapping("/logs/")
public class LoggerController {

    private final LoggerService loggerService;

    @Autowired
    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @RequestMapping(value = {"all", "search"}, method = RequestMethod.GET)
    public String allLogs(Model model) {
        model.addAttribute("view", "logger/loggerTable");

        if (!model.containsAttribute("logs")) {
            model.addAttribute("logs", this.loggerService.findAll());
        }

        return "base-layout";
    }

    @GetMapping("clearAll")
    public String clearAll() {
        this.loggerService.removeAll();

        return "redirect:/logs/all";
    }

    @PostMapping("search")
    public String search(HttpServletRequest request, RedirectAttributes attributes) {
        try {
            List<LoggerResponseModel> logs = this.loggerService.search(request.getParameter("search"), request.getParameter("username"));
            attributes.addFlashAttribute("logs", logs);
        } catch (NullPointerException npe) {
            attributes.addFlashAttribute("logs", new ArrayList<>());
        }

        return "redirect:/logs/search";
    }

}
