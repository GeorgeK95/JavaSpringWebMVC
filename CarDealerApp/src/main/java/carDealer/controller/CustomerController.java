package carDealer.controller;

import carDealer.model.request.AddCustomerRequestModel;
import carDealer.model.response.CustomerResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.service.CustomerServices;
import carDealer.service.SaleServices;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/customers/")
public class CustomerController {

    private final CustomerServices customerServices;
    private final SaleServices saleServices;

    @Autowired
    public CustomerController(CustomerServices customerServices, SaleServices saleServices) {
        this.customerServices = customerServices;
        this.saleServices = saleServices;
    }

    @GetMapping("all/ascending")
    public String ascending(Model model) {
        List<CustomerResponseModel> orderedCustomers = this.customerServices.orderedAscendingCustomers();

        model.addAttribute("customers", orderedCustomers);
        model.addAttribute("view", "customer/customersTable");

        return "base-layout";
    }

    @GetMapping("all/descending")
    public String descending(Model model) {
        List<CustomerResponseModel> orderedCustomers = this.customerServices.orderedDescendingCustomers();

        model.addAttribute("customers", orderedCustomers);
        model.addAttribute("view", "customer/customersTable");

        return "base-layout";
    }

    @GetMapping("{id}")
    public String totalSalesByCustomer(@PathVariable Long id, Model model) {
        CustomerResponseModel customer = this.customerServices.findOne(id, CustomerResponseModel.class);
//        List<SaleResponseModel> totalSalesByCustomer = this.saleServices.totalSalesByCustomer(id);
        List<SaleResponseModel> totalSalesByCustomer = DTOConvertUtil.convert(customer.getSales(), SaleResponseModel.class);

        model.addAttribute("salesCount", totalSalesByCustomer.size());
        model.addAttribute("customerName", customer.getName());

        Double money = this.customerServices.getTotalMoneySpentForCars(totalSalesByCustomer);

        model.addAttribute("money", money);
        model.addAttribute("view", "customer/customerSalesTable");

        return "base-layout";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("view", "customer/add");

        return "base-layout";
    }

    @PostMapping("add")
    public String addProcess(RedirectAttributes model, AddCustomerRequestModel customerRequestModel) {
        this.customerServices.add(customerRequestModel, model);

        return "redirect:/customers/add";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("view", "customer/edit");
        model.addAttribute("customerId", id);
        model.addAttribute("customer", this.customerServices.findOne(id, CustomerResponseModel.class));

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editProcess(RedirectAttributes model, AddCustomerRequestModel customerRequestModel,
                              @PathVariable Long id) {
        this.customerServices.edit(id, customerRequestModel, model);

        return "redirect:/customers/edit/".concat(String.valueOf(id));
    }
}
