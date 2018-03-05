package carDealer.controller;

import carDealer.model.request.AddSaleRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.service.CarServices;
import carDealer.service.CustomerServices;
import carDealer.service.SaleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    private final CustomerServices customerServices;

    @Autowired
    public SaleController(SaleServices saleServices, CarServices carServices, CustomerServices customerServices) {
        this.saleServices = saleServices;
        this.carServices = carServices;
        this.customerServices = customerServices;
    }

    @GetMapping("")
    public String allSales(Model model) {
        List<SaleResponseModel> all = this.saleServices.findAll();

        model.addAttribute("sales", all);

        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }

    @GetMapping("/add")
    public String addSale(Model model) {
        model.addAttribute("view", "sale/add");
        model.addAttribute("customers", this.customerServices.findAll());
        model.addAttribute("cars", this.carServices.findAll());
        model.addAttribute("discounts", new ArrayList<BigDecimal>() {{
            add(new BigDecimal(0.10));
            add(new BigDecimal(0.20));
            add(new BigDecimal(0.30));
            add(new BigDecimal(0.40));
            add(new BigDecimal(0.50));
            add(new BigDecimal(0.60));
            add(new BigDecimal(0.70));
            add(new BigDecimal(0.80));
            add(new BigDecimal(0.90));
        }});

        return "base-layout";
    }

    @PostMapping("/add")
    public String addSaleProcess(AddSaleRequestModel saleRequestModel, RedirectAttributes model,
                                 HttpServletRequest request) {
        return "redirect:/Sales/review";
    }

    @GetMapping("/review")
    public String reviewSale(Model model) {
        model.addAttribute("view", "sale/add");

        return "base-layout";
    }

    @PostMapping("/review")
    public String reviewSaleProcess(AddSaleRequestModel saleRequestModel, RedirectAttributes model) {
        this.saleServices.addSale(saleRequestModel, model);

        return "redirect:/Sales/review";
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
