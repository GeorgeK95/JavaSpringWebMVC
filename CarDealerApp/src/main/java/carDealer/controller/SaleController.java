package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.model.entity.Part;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddSaleReviewRequestModel;
import carDealer.model.response.*;
import carDealer.repository.PartRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/Sales")
public class SaleController {

    public static final int YOUNG_DRIVER_DISCOUNT_PERCENTAGE = 5;

    private final SaleServices saleServices;

    private final CarServices carServices;

    private final CustomerServices customerServices;

    private final PartRepository partRepository;

    @Autowired
    public SaleController(SaleServices saleServices, CarServices carServices, CustomerServices customerServices, PartRepository partRepository) {
        this.saleServices = saleServices;
        this.carServices = carServices;
        this.customerServices = customerServices;
        this.partRepository = partRepository;
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
    public ModelAndView addSaleProcess(@RequestParam Long carId,
                                       @RequestParam Long customerId,
                                       @RequestParam Double discount,
                                       RedirectAttributes redirectAttributes) {

        CarBasicInfoResponseModel car = this.carServices.findOne(carId, CarBasicInfoResponseModel.class);
        Double totalCarPrice = this.partRepository.findCarPartsById(carId).stream().mapToDouble(Part::getPrice).sum();
        car.setPrice(totalCarPrice);
        car.setFinalCarPrice(totalCarPrice - (totalCarPrice * discount));

        CustomerBasicInfoResponseModel customer = this.customerServices.findOne(customerId, CustomerBasicInfoResponseModel.class);

        SaleInfoResponseModel saleInfoResponseModel = new SaleInfoResponseModel();
        saleInfoResponseModel.setCar(car);
        saleInfoResponseModel.setCustomer(customer);
        saleInfoResponseModel.setDiscount(discount);

        redirectAttributes.addFlashAttribute("sale", saleInfoResponseModel);

        return LoggerService.constructModelAndView(
                "redirect:/Sales/review",
                "someUser: TOBEDONE",
                Operation.ADD.toString(),
                TableEnum.SALE.toString()
        );
    }

    @GetMapping("/review")
    public String reviewSale(Model model) {
        model.addAttribute("view", "sale/review");

        SaleInfoResponseModel saleInfoResponseModel = this.saleServices.constructSaleInfoResponseModelObject(model);

        model.addAttribute("sale", saleInfoResponseModel);

        return "base-layout";
    }

    @PostMapping("/review")
    public String reviewSaleProcess(@ModelAttribute AddSaleReviewRequestModel addSaleReviewRequestModel) {
        this.saleServices.addSale(addSaleReviewRequestModel);

        return "redirect:/Sales/review";
    }

    @GetMapping("/{id}")
    public String saleById(@PathVariable Long id, Model model) {
        CarResponseModel carById = this.carServices.findOne(id, CarResponseModel.class);
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
    public String discountedByPercent(@PathVariable Double percent, Model model) {
        List<SaleResponseModel> allSales = this.saleServices.findAllDiscountedByPercent(
                percent / 100
        );

        model.addAttribute("sales", allSales);
        model.addAttribute("view", "sale/salesTable");

        return "base-layout";
    }
}
