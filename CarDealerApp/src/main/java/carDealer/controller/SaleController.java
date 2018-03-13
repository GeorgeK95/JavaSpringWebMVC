package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.annotations.PreAuthenticated;
import carDealer.model.entity.Part;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddSaleRequestModel;
import carDealer.model.request.AddSaleReviewRequestModel;
import carDealer.model.response.*;
import carDealer.repository.PartRepository;
import carDealer.service.api.ICarServices;
import carDealer.service.api.ICustomerServices;
import carDealer.service.api.ILoggerService;
import carDealer.service.api.ISaleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static carDealer.model.response.SaleInfoResponseModel.YOUNG_DRIVER_DISCOUNT_PERCENTAGE;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/Sales")
public class SaleController {

    private final carDealer.service.api.ISaleServices ISaleServices;

    private final carDealer.service.api.ICarServices ICarServices;

    private final carDealer.service.api.ICustomerServices ICustomerServices;

    private final PartRepository partRepository;

    @Autowired
    public SaleController(ISaleServices ISaleServices, ICarServices ICarServices, ICustomerServices ICustomerServices, PartRepository partRepository) {
        this.ISaleServices = ISaleServices;
        this.ICarServices = ICarServices;
        this.ICustomerServices = ICustomerServices;
        this.partRepository = partRepository;
    }

    @GetMapping("")
    public String allSales(Model model) {
        List<SaleResponseModel> all = this.ISaleServices.findAll();

        model.addAttribute("sales", all);

        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }

    @GetMapping("/add")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String addSale(Model model) {
        model.addAttribute("view", "sale/add");
        model.addAttribute("customers", this.ICustomerServices.findAll());
        model.addAttribute("cars", this.ICarServices.findAll());
        Double[] discountsArray = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        model.addAttribute("discounts", Arrays.asList(discountsArray));

        return "base-layout";
    }

    @PostMapping("/add")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String addSaleProcess(@Valid @ModelAttribute AddSaleRequestModel addSaleRequestModel,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("add_sale_error",
                    "Some of the field are empty or filled with incorrect data.");
            return "redirect:/Sales/add";
        }

        CarBasicInfoResponseModel car = this.ICarServices.findOne(addSaleRequestModel.getCarId(), CarBasicInfoResponseModel.class);

        Double totalCarPrice = this.partRepository.findCarPartsById(addSaleRequestModel.getCarId()).stream().mapToDouble(Part::getPrice).sum();
        car.setPrice(totalCarPrice);

        CustomerBasicInfoResponseModel customer = this.ICustomerServices.findOne(addSaleRequestModel.getCustomerId(), CustomerBasicInfoResponseModel.class);

        Double discount = addSaleRequestModel.getDiscount();
        if (customer.isYoungDriver()) discount += YOUNG_DRIVER_DISCOUNT_PERCENTAGE;
        car.setFinalCarPrice(totalCarPrice - (totalCarPrice * discount / 100));

        SaleInfoResponseModel saleInfoResponseModel = new SaleInfoResponseModel(customer, car, addSaleRequestModel.getDiscount());

        redirectAttributes.addFlashAttribute("sale", saleInfoResponseModel);

        return "redirect:/Sales/review";
    }

    @GetMapping("/review")
    public String reviewSale(Model model) {
        model.addAttribute("view", "sale/review");

        SaleInfoResponseModel saleInfoResponseModel = this.ISaleServices.constructSaleInfoResponseModelObject(model);

        model.addAttribute("sale", saleInfoResponseModel);

        return "base-layout";
    }

    @PostMapping("/review")
    @LoggedAction
    public ModelAndView reviewSaleProcess(@ModelAttribute AddSaleReviewRequestModel addSaleReviewRequestModel,
                                          RedirectAttributes attributes) {
        this.ISaleServices.addSale(addSaleReviewRequestModel, attributes);

        return ILoggerService.constructModelAndView(
                "redirect:/Sales/",
                "someUser: TOBEDONE",
                Operation.ADD.toString(),
                TableEnum.SALE.toString()
        );
    }

    @GetMapping("/{id}")
    public String saleById(@PathVariable Long id, Model model) {
        CarResponseModel carById = this.ICarServices.findOne(id, CarResponseModel.class);
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
        List<SaleResponseModel> allSales = this.ISaleServices.findAllDiscounted();

        model.addAttribute("sales", allSales);
        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }

    @GetMapping("/discounted/{percent}")
    public String discountedByPercent(@PathVariable Double percent, Model model) {
        List<SaleResponseModel> allSales = this.ISaleServices.findAllDiscountedByPercent(
                percent / 100
        );

        model.addAttribute("sales", allSales);
        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }
}
