package carDealer.controller;

import carDealer.model.request.AddPartRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.model.response.PartResponseModel;
import carDealer.model.response.SupplierResponseModel;
import carDealer.service.CarServices;
import carDealer.service.PartServices;
import carDealer.service.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
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

    @GetMapping("/cars/{id}/parts")
    public String carsWithParts(@PathVariable Long id, Model model) {
        CarResponseModel car = this.carServices.findById(id);
        List<PartResponseModel> partsByCarId = this.partServices.getPartsByCarId(id);

        model.addAttribute("car", car);
        model.addAttribute("parts", partsByCarId);
        model.addAttribute("view", "part/partsTable");

        return "base-layout";
    }

    @GetMapping("/parts/add")
    public String add(Model model) {
        List<SupplierResponseModel> allSuppliers = this.supplierServices.findAll();

        model.addAttribute("view", "part/add");
        model.addAttribute("suppliers", allSuppliers);

        return "base-layout";
    }

    @PostMapping("/parts/add")
    public String addProcess(AddPartRequestModel requestModel) {
        return "redirect:/parts/add";
    }
}
