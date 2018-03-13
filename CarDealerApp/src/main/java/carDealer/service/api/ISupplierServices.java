package carDealer.service.api;

import carDealer.model.request.AddSupplierRequestModel;
import carDealer.model.response.SupplierResponseModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface ISupplierServices {
    List<SupplierResponseModel> filterLocalSuppliers();

    List<SupplierResponseModel> filterImportersSuppliers();

    List<SupplierResponseModel> findAll();

    SupplierResponseModel findOne(Long id);

    void addSale(AddSupplierRequestModel supplierRequestModel, RedirectAttributes attributes);

    void editSupply(Long id, AddSupplierRequestModel requestModel);

    void deleteSupply(Long id);
}
