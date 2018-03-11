package residentEvilApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import residentEvilApp.model.enums.MagnitudeType;
import residentEvilApp.model.enums.MutationType;
import residentEvilApp.model.request.AddVirusRequestModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
@Controller
@RequestMapping("/viruses/")
public class VirusController {

    @ModelAttribute(name = "magnitudesList")
    public List<String> getMagnitude() {
        return Arrays.stream(MagnitudeType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @ModelAttribute(name = "mutationsList")
    public List<String> getMutations() {
        return Arrays.stream(MutationType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @ModelAttribute(name = "capitalsList")
    public List<String> getCapitals() {
        return Arrays.asList("Sofia", "Pld", "Bs");
    }

    @GetMapping("add")
    public String addVirus(@ModelAttribute AddVirusRequestModel requestModel, Model model) {
        model.addAttribute("view", "virus/add");

        return "base-layout";
    }

    @PostMapping("add")
    public String addVirusProcess(@ModelAttribute AddVirusRequestModel requestModel, HttpServletRequest request) {
        return "redirect:/viruses/show";
    }

    @GetMapping("show")
    public String showViruses(Model model) {
        model.addAttribute("view", "virus/show");

        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String editVirus(Model model, @PathVariable Long id) {
        model.addAttribute("view", "virus/show");

        return "base-layout";
    }

    @GetMapping("delete/{id}")
    public String deleteVirus(Model model, @PathVariable Long id) {
        model.addAttribute("view", "virus/show");

        return "base-layout";
    }
}
