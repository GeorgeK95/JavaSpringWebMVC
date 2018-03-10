package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddSupplierRequestModel;
import carDealer.model.response.SupplierResponseModel;
import carDealer.service.LoggerService;
import carDealer.service.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/suppliers/")
public class SupplierController {

    private final SupplierServices supplierServices;

    @Autowired
    public SupplierController(SupplierServices services) {
        this.supplierServices = services;
    }

    @GetMapping("add")
    public String addSupplier(Model model) {
        model.addAttribute("view", "supplier/add");

        return "base-layout";
    }

    @LoggedAction
    @PostMapping("add")
    public ModelAndView addSupplierProcess(@ModelAttribute AddSupplierRequestModel supplierRequestModel, RedirectAttributes attributes) {
        this.supplierServices.addSale(supplierRequestModel, attributes);

        return LoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.ADD.toString(),
                TableEnum.SUPPLIER.toString()
        );
    }

    @GetMapping("local")
    public String local(Model model) {
        List<SupplierResponseModel> allByIsImportedTrue = this.supplierServices.filterLocalSuppliers();

        model.addAttribute("suppliers", allByIsImportedTrue);
        model.addAttribute("view", "supplier/suppliersTable");

        return "base-layout";
    }

    @GetMapping("importers")
    public String importers(Model model) {
        List<SupplierResponseModel> allByIsImportedFalse = this.supplierServices.filterImportersSuppliers();

        model.addAttribute("suppliers", allByIsImportedFalse);
        model.addAttribute("view", "supplier/suppliersTable");

        return "base-layout";
    }

    @GetMapping("all")
    public String allSuppliers(Model model) {
        List<SupplierResponseModel> allSuppliers = this.supplierServices.findAll();

        model.addAttribute("suppliers", allSuppliers);
        model.addAttribute("view", "supplier/suppliersActionTable");

        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String editSupplier(Model model, @PathVariable Long id) {
        model.addAttribute("view", "supplier/edit");
        model.addAttribute("supplier", this.supplierServices.findOne(id));

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    @LoggedAction
    public ModelAndView editSupplierProcess(@PathVariable Long id, @ModelAttribute AddSupplierRequestModel requestModel) {
        this.supplierServices.editSupply(id, requestModel);

        return LoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.EDIT.toString(),
                TableEnum.SUPPLIER.toString()
        );
//        return "redirect:/suppliers/edit/".concat(String.valueOf(id));
    }

    @GetMapping("delete/{id}")
    public String deleteSupplier(Model model, @PathVariable Long id) {
        model.addAttribute("view", "supplier/delete");
        model.addAttribute("supplier", this.supplierServices.findOne(id));

        return "base-layout";
    }

    @LoggedAction
    @PostMapping("delete/{id}")
    public ModelAndView deleteSupplierProcess(@PathVariable Long id) {
        this.supplierServices.deleteSupply(id);

        return LoggerService.constructModelAndView(
                "redirect:/suppliers/all/",
                "someUser: TOBEDONE",
                Operation.DELETE.toString(),
                TableEnum.SUPPLIER.toString()
        );
    }
}
