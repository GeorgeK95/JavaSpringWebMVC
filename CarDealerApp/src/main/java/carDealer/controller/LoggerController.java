package carDealer.controller;

import carDealer.annotations.PreAuthenticated;
import carDealer.model.response.LoggerResponseModel;
import carDealer.service.api.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
@Controller
@RequestMapping("/logs/")
public class LoggerController {

    private final ILoggerService ILoggerService;

    @Autowired
    public LoggerController(ILoggerService ILoggerService) {
        this.ILoggerService = ILoggerService;
    }

    @PreAuthenticated(loggedInUserRequirement = true)
    @RequestMapping(value = {"all", "search"}, method = RequestMethod.GET)
    public String allLogs(Model model) {
        model.addAttribute("view", "logger/loggerTable");

        if (!model.containsAttribute("logs")) {
            model.addAttribute("logs", this.ILoggerService.findAll());
        }

        return "base-layout";
    }

    @GetMapping("clearAll")
    public String clearAll() {
        this.ILoggerService.removeAll();

        return "redirect:/logs/all";
    }

    @PostMapping("search")
    public String search(HttpServletRequest request, RedirectAttributes attributes) {
        try {
            List<LoggerResponseModel> logs = this.ILoggerService.search(request.getParameter("search"), request.getParameter("username"));
            attributes.addFlashAttribute("logs", logs);
        } catch (NullPointerException npe) {
            attributes.addFlashAttribute("logs", new ArrayList<>());
        }

        return "redirect:/logs/search";
    }

}
