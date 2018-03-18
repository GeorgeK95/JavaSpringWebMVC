package residentEvilApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import residentEvilApp.model.entity.Capital;
import residentEvilApp.model.enums.CreatorType;
import residentEvilApp.model.enums.MagnitudeType;
import residentEvilApp.model.enums.MutationType;
import residentEvilApp.model.request.AddVirusRequestModel;
import residentEvilApp.model.request.CapitalResponseModel;
import residentEvilApp.model.request.EditVirusRequestModel;
import residentEvilApp.model.response.EditVirusResponseModel;
import residentEvilApp.service.contacts.ICapitalService;
import residentEvilApp.service.contacts.IVirusService;
import residentEvilApp.util.DTOConverter;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
@Controller
@RequestMapping("/viruses/")
public class VirusController {

    private final ICapitalService capitalService;
    private final IVirusService virusService;

    @Autowired
    public VirusController(ICapitalService capitalService, IVirusService virusService) {
        this.capitalService = capitalService;
        this.virusService = virusService;
    }

    @ModelAttribute(name = "magnitudesList")
    public List<String> getMagnitude() {
        return Arrays.stream(MagnitudeType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @ModelAttribute(name = "creatorsList")
    public List<String> getCreators() {
        return Arrays.stream(CreatorType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @ModelAttribute(name = "mutationsList")
    public List<String> getMutations() {
        return Arrays.stream(MutationType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @ModelAttribute(name = "capitalsList")
    public List<CapitalResponseModel> getCapitals() {
        List<Capital> capitals = this.capitalService.findAll();
        return DTOConverter.convert(capitals, CapitalResponseModel.class);
    }

    @GetMapping("add")
    public String addVirus(@ModelAttribute AddVirusRequestModel requestModel, Model model) {
        model.addAttribute("view", "virus/add");

        return "base-layout";
    }

    @PostMapping("add")
    public String addVirusProcess(@Valid @ModelAttribute AddVirusRequestModel requestModel, BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "virus/add");
            return "base-layout";
        }
        this.virusService.addVirus(requestModel);
        return "redirect:/viruses/show";
    }

    @GetMapping("show")
    public String showViruses(Model model) {
        model.addAttribute("view", "virus/show");
        model.addAttribute("viruses", this.virusService.findAll());
        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String editVirus(Model model, @PathVariable Long id) {
        model.addAttribute("editVirusModel", this.virusService.findOne(id, EditVirusResponseModel.class));
        model.addAttribute("view", "virus/edit");

        return "base-layout";
    }

    @GetMapping("delete/{id}")
    public String deleteVirus(Model model, @PathVariable Long id) {
        model.addAttribute("deleteVirusModel", this.virusService.findOne(id, EditVirusResponseModel.class));
        model.addAttribute("view", "virus/delete");

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editVirusProcess(Model model, @PathVariable Long id, @ModelAttribute @Valid EditVirusRequestModel requestModel,
                                   BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("view", "virus/edit");
            model.addAttribute("editVirusModel", requestModel);
            return "base-layout";
        }
        this.virusService.editVirus(id, requestModel);

        return "redirect:/viruses/show";
    }

    @PostMapping("delete/{id}")
    public String deleteVirusProcess(@PathVariable Long id) {
        this.virusService.deleteVirus(id);

        return "redirect:/viruses/show";
    }
}
