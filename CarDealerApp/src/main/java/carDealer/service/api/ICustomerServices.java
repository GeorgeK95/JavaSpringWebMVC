package carDealer.service.api;

import carDealer.model.request.AddCustomerRequestModel;
import carDealer.model.response.CustomerResponseModel;
import carDealer.model.response.SaleResponseModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface ICustomerServices {
    List<CustomerResponseModel> orderedAscendingCustomers();

    List<CustomerResponseModel> orderedDescendingCustomers();

    Double getTotalMoneySpentForCars(List<SaleResponseModel> totalSalesByCustomer);

    <T> T findOne(Long id, Class<T> clazz);

    void add(AddCustomerRequestModel customerRequestModel, RedirectAttributes model);

    void edit(Long id, AddCustomerRequestModel customerRequestModel,
              RedirectAttributes model);

    List<CustomerResponseModel> findAll();
}
