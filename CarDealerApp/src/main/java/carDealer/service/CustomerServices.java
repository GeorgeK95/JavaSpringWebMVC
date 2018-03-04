package carDealer.service;

import carDealer.model.Customer;
import carDealer.model.request.AddCustomerRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.model.response.CustomerResponseModel;
import carDealer.model.response.PartResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.repository.CustomerRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static carDealer.utils.DateUtils.getDateDiff;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class CustomerServices {
    private static final int YEAR_DAYS = 365;
    private static final int NEEDED_YEARS = 18;
    private static final int NEEDED_DAYS = YEAR_DAYS * NEEDED_YEARS;

    private long dateDiff;

    private CustomerRepository repository;

    @Autowired
    public CustomerServices(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerResponseModel> orderedAscendingCustomers() {
        return DTOConvertUtil.convert(this.repository.orderedAscendingCustomers(), CustomerResponseModel.class);
    }

    public List<CustomerResponseModel> orderedDescendingCustomers() {
        return DTOConvertUtil.convert(this.repository.orderedDescendingCustomers(), CustomerResponseModel.class);
    }

    public BigDecimal getTotalMoneySpentForCars(List<SaleResponseModel> totalSalesByCustomer) {
        BigDecimal money = new BigDecimal(0);
        for (SaleResponseModel saleResponseModel : totalSalesByCustomer) {
            CarResponseModel car = saleResponseModel.getCar();
            for (PartResponseModel partResponseModel : car.getParts()) {
                money = money.add(partResponseModel.getPrice());
            }
        }
        return money;
    }

    public CustomerResponseModel findOne(Long id) {
        return DTOConvertUtil.convert(this.repository.findOne(id), CustomerResponseModel.class);
    }

    public void add(AddCustomerRequestModel customerRequestModel, RedirectAttributes model) {
        Date date = Date.from(customerRequestModel.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.dateDiff = getDateDiff(new Date(), date, TimeUnit.DAYS);

        if (!this.validateDate(model)) {
            return;
        }

        customerRequestModel.setBirthDate(date);
        Customer customer = DTOConvertUtil.convert(customerRequestModel, Customer.class);

        this.setYoungDriver(customer, model, customerRequestModel.getName().concat(" added successfully."));

        this.repository.saveAndFlush(customer);
    }


    public void edit(Long id, AddCustomerRequestModel customerRequestModel,
                     RedirectAttributes model) {
        Customer toEdit = this.repository.findOne(id);
        Date date = Date.from(customerRequestModel.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.dateDiff = getDateDiff(new Date(), date, TimeUnit.DAYS);

        if (!this.validateDate(model)) {
            return;
        }

        toEdit.setName(customerRequestModel.getName());
        toEdit.setBirthDate(date);
        this.setYoungDriver(toEdit, model, "Changes were made successfully.");

        this.repository.saveAndFlush(toEdit);
    }

    private boolean validateDate(RedirectAttributes model) {
        if (this.dateDiff >= 0) {
            model.addFlashAttribute("customer_notification",
                    "Invalid date !");
            return false;
        }

        return true;
    }

    private void setYoungDriver(Customer customer, RedirectAttributes model, String value) {
        if (Math.abs(this.dateDiff) >= NEEDED_DAYS) {
            customer.setYoungDriver(true);
        } else {
            customer.setYoungDriver(false);
        }

        model.addFlashAttribute("customer_notification",
                value);
    }
}
