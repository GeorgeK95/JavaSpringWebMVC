package carDealer.service.api;

import carDealer.model.request.AddSaleReviewRequestModel;
import carDealer.model.response.SaleInfoResponseModel;
import carDealer.model.response.SaleResponseModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface ISaleServices {
    List<SaleResponseModel> findAllDiscounted();

    List<SaleResponseModel> findAllDiscountedByPercent(Double percent);

    List<SaleResponseModel> totalSalesByCustomer(Long id);

    List<SaleResponseModel> findAll();

    void addSale(AddSaleReviewRequestModel saleRequestModel, RedirectAttributes attributes);

    SaleInfoResponseModel constructSaleInfoResponseModelObject(Model model);
}
