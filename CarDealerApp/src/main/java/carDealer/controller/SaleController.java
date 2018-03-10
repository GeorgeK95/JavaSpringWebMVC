package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddSaleRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.model.response.CustomerResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.service.CarServices;
import carDealer.service.CustomerServices;
import carDealer.service.LoggerService;
import carDealer.service.SaleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/Sales")
public class SaleController {

    public static final int YOUNG_DRIVER_DISCOUNT_PERCENTAGE = 5;

    private Map<String, Object> cachedData;

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
        Double[] discountsArray = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        model.addAttribute("discounts", Arrays.asList(discountsArray));

        return "base-layout";
    }

    @PostMapping("/add")
    @LoggedAction
    public ModelAndView addSaleProcess(@ModelAttribute AddSaleRequestModel saleRequestModel, RedirectAttributes model,
                                       HttpServletRequest request) {
//        model.addFlashAttribute("saleRequestModel", saleRequestModel);

        CarResponseModel car = this.carServices.findOne(saleRequestModel.getCarId());
        CustomerResponseModel customer = this.customerServices.findOne(saleRequestModel.getCustomerId());
        model.addFlashAttribute("customer", customer);

        /*this.cachedData = new HashMap<>();
        this.cachedData.put("customer", customer);
        this.cachedData.put("car", car);*/

        /*model.addFlashAttribute("customerName", customer.getName());
        model.addFlashAttribute("carName", car.getMake() + " " + car.getModel());
        model.addFlashAttribute("discount", saleRequestModel.getDiscount());*/

        /*BigDecimal discount = saleRequestModel.getDiscount();
        String discountMessage = "";
        if (customer.isYoungDriver()) {
            discount = discount.add(new BigDecimal(YOUNG_DRIVER_DISCOUNT_PERCENTAGE));
            discountMessage = String.format(" (+%s%)", String.valueOf(YOUNG_DRIVER_DISCOUNT_PERCENTAGE));
        }

        model.addAttribute("discountStringValue", discount.toString().concat("%")
                .concat(discountMessage));*/

        return LoggerService.constructModelAndView(
                "redirect:/Sales/review",
                "someUser: TOBEDONE",
                Operation.ADD.toString(),
                TableEnum.SALE.toString()
        );
    }

    @GetMapping("/review")
    public String reviewSale(Model model) {
        Map<String, Object> stringObjectMap = model.asMap();

        model.addAttribute("view", "sale/review");
        model.addAllAttributes(stringObjectMap);

        return "base-layout";
    }

    @PostMapping("/review")
    public String reviewSaleProcess(/*AddSaleRequestModel saleRequestModel,*/
                                    RedirectAttributes model,
                                    HttpServletRequest request) {
        this.saleServices.addSale(null, model);

        return "redirect:/Sales/review";
    }

    @GetMapping("/{id}")
    public String saleById(@PathVariable Long id, Model model) {
        CarResponseModel carById = this.carServices.findOne(id);
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
