package carDealer.controller;

import carDealer.model.response.CarResponseModel;
import carDealer.service.CarServices;
import carDealer.service.PartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/cars/")
public class CarController {

    private final CarServices carServices;

    private final PartServices partServices;

    @Autowired
    public CarController(CarServices carServices, PartServices partServices) {
        this.carServices = carServices;
        this.partServices = partServices;
    }

    @GetMapping("all")
    public String allCars(Model model) {
        List<CarResponseModel> allCars = this.carServices.findAll();

        model.addAttribute("view", "car/carsTable");
        model.addAttribute("cars", allCars);

        return "base-layout";
    }

    @GetMapping("{make}")
    public String carsFromMake(@PathVariable String make, Model model) {
        List<CarResponseModel> cars = this.carServices.orderedAscendingCustomers(make);

        model.addAttribute("cars", cars);
        model.addAttribute("view", "car/carsTable");

        return "base-layout";
    }

}
