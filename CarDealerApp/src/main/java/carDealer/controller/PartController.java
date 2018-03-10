package carDealer.controller;

import carDealer.model.request.AddPartRequestModel;
import carDealer.model.response.PartResponseModel;
import carDealer.model.response.SupplierResponseModel;
import carDealer.service.CarServices;
import carDealer.service.PartServices;
import carDealer.service.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/parts/")
public class PartController {

    private final CarServices carServices;
    private final PartServices partServices;
    private final SupplierServices supplierServices;

    @Autowired
    public PartController(CarServices carServices, PartServices partServices, SupplierServices supplierServices) {
        this.carServices = carServices;
        this.partServices = partServices;
        this.supplierServices = supplierServices;
    }

    @GetMapping("all")
    public String all(Model model) {
        List<PartResponseModel> allParts = this.partServices.findAll();

        model.addAttribute("parts", allParts);
        model.addAttribute("view", "part/partsTable");
        model.addAttribute("editDelete", "delete");

        return "base-layout";
    }

    //CRUD
    @GetMapping("add")
    public String add(Model model) {
        List<SupplierResponseModel> allSuppliers = this.supplierServices.findAll();

        model.addAttribute("view", "part/add");
        model.addAttribute("suppliers", allSuppliers);

        return "base-layout";
    }

    @PostMapping("add")
    public String addProcess(@ModelAttribute AddPartRequestModel requestModel, RedirectAttributes model) {
        this.partServices.addPart(requestModel, model);

        return "redirect:/parts/add";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        PartResponseModel partToEdit = this.partServices.findOne(id);

        model.addAttribute("view", "part/edit");
        model.addAttribute("part", partToEdit);

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editProcess(@PathVariable Long id, AddPartRequestModel requestModel,
                              RedirectAttributes model) {
        this.partServices.editPart(id, requestModel, model);

        return "redirect:/parts/edit/".concat(String.valueOf(id));
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        PartResponseModel partToDelete = this.partServices.findOne(id);

        model.addAttribute("part", partToDelete);
        model.addAttribute("view", "part/delete");

        return "base-layout";
    }

    @PostMapping("delete/{id}")
    public String deleteProcess(@PathVariable Long id, AddPartRequestModel requestModel,
                                RedirectAttributes model) {
        this.partServices.deletePart(id, requestModel, model);

        return "redirect:/parts/all";
    }


}
