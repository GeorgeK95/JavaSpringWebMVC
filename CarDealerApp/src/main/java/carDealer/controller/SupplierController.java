package carDealer.controller;

import carDealer.model.response.SupplierResponseModel;
import carDealer.service.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("view", "supplier/suppliersTable");

        return "base-layout";
    }
}
