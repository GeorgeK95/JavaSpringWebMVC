package carDealer.controller;

import carDealer.model.response.CarResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.service.CarServices;
import carDealer.service.SaleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/Sales")
public class SaleController {

    private final SaleServices saleServices;

    private final CarServices carServices;

    @Autowired
    public SaleController(SaleServices saleServices, CarServices carServices) {
        this.saleServices = saleServices;
        this.carServices = carServices;
    }

    @GetMapping("")
    public String allSales(Model model) {
        List<SaleResponseModel> all = this.saleServices.findAll();

        model.addAttribute("sales", all);

        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }

    @GetMapping("/{id}")
    public String saleById(@PathVariable Long id, Model model) {
        CarResponseModel carById = this.carServices.findById(id);
        String customerName = carById.getSale().getCustomer().getName();

        model.addAttribute("customerName", customerName);

        model.addAttribute("cars", new ArrayList<CarResponseModel>() {{
            add(carById);
        }});

        model.addAttribute("view", "car/carsTable");

        return "base-layout";
    }


    @GetMapping("/discounted")
    public String discountedSales(Model model) {
        List<SaleResponseModel> allSales = this.saleServices.findAllDiscounted();

        model.addAttribute("sales", allSales);
        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }

    @GetMapping("/discounted/{percent}")
    public String discountedByPercent(@PathVariable BigDecimal percent, Model model) {
        List<SaleResponseModel> allSales = this.saleServices.findAllDiscountedByPercent(
                percent.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
        );

        model.addAttribute("sales", allSales);
        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }
}
