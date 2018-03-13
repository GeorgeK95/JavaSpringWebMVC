package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.annotations.PreAuthenticated;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddSupplierRequestModel;
import carDealer.model.response.SupplierResponseModel;
import carDealer.service.api.ILoggerService;
import carDealer.service.api.ISupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/suppliers/")
public class SupplierController {

    private final ISupplierServices ISupplierServices;

    @Autowired
    public SupplierController(ISupplierServices services) {
        this.ISupplierServices = services;
    }

    @GetMapping("add")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String addSupplier(Model model) {
        model.addAttribute("view", "supplier/add");

        return "base-layout";
    }

    @LoggedAction
    @PostMapping("add")
    @PreAuthenticated(loggedInUserRequirement = true)
    public ModelAndView addSupplierProcess(@ModelAttribute AddSupplierRequestModel supplierRequestModel, RedirectAttributes attributes) {
        this.ISupplierServices.addSale(supplierRequestModel, attributes);

        return ILoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.ADD.toString(),
                TableEnum.SUPPLIER.toString()
        );
    }

    @GetMapping("local")
    public String local(Model model) {
        List<SupplierResponseModel> allByIsImportedTrue = this.ISupplierServices.filterLocalSuppliers();

        model.addAttribute("suppliers", allByIsImportedTrue);
        model.addAttribute("view", "supplier/suppliersTable");

        return "base-layout";
    }

    @GetMapping("importers")
    public String importers(Model model) {
        List<SupplierResponseModel> allByIsImportedFalse = this.ISupplierServices.filterImportersSuppliers();

        model.addAttribute("suppliers", allByIsImportedFalse);
        model.addAttribute("view", "supplier/suppliersTable");

        return "base-layout";
    }

    @GetMapping("all")
    public String allSuppliers(Model model) {
        List<SupplierResponseModel> allSuppliers = this.ISupplierServices.findAll();

        model.addAttribute("suppliers", allSuppliers);
        model.addAttribute("view", "supplier/suppliersActionTable");

        return "base-layout";
    }

    @GetMapping("edit/{id}")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String editSupplier(Model model, @PathVariable Long id) {
        model.addAttribute("view", "supplier/edit");
        model.addAttribute("supplier", this.ISupplierServices.findOne(id));

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    @PreAuthenticated(loggedInUserRequirement = true)
    @LoggedAction
    public ModelAndView editSupplierProcess(@PathVariable Long id, @ModelAttribute AddSupplierRequestModel requestModel) {
        this.ISupplierServices.editSupply(id, requestModel);

        return ILoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.EDIT.toString(),
                TableEnum.SUPPLIER.toString()
        );
//        return "redirect:/suppliers/edit/".concat(String.valueOf(id));
    }

    @GetMapping("delete/{id}")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String deleteSupplier(Model model, @PathVariable Long id) {
        model.addAttribute("view", "supplier/delete");
        model.addAttribute("supplier", this.ISupplierServices.findOne(id));

        return "base-layout";
    }

    @LoggedAction
    @PostMapping("delete/{id}")
    @PreAuthenticated(loggedInUserRequirement = true)
    public ModelAndView deleteSupplierProcess(@PathVariable Long id) {
        this.ISupplierServices.deleteSupply(id);

        return ILoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.DELETE.toString(),
                TableEnum.SUPPLIER.toString()
        );
    }
}
