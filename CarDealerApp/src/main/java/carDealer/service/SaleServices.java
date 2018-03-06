package carDealer.service;

import carDealer.model.entity.Sale;
import carDealer.model.request.AddSaleRequestModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.repository.SaleRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class SaleServices {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServices(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<SaleResponseModel> findAllDiscounted() {
        return DTOConvertUtil.convert(this.saleRepository.findAllDiscounted(), SaleResponseModel.class);
    }

    public List<SaleResponseModel> findAllDiscountedByPercent(BigDecimal percent) {
        return DTOConvertUtil.convert(this.saleRepository.findAllDiscountedByPercent(percent), SaleResponseModel.class);
    }

    public List<SaleResponseModel> totalSalesByCustomer(Long id) {
        return DTOConvertUtil.convert(this.saleRepository.totalSalesByCustomer(id), SaleResponseModel.class);
    }

    public List<SaleResponseModel> findAll() {
        return DTOConvertUtil.convert(this.saleRepository.findAll(), SaleResponseModel.class);
    }

    public void addSale(AddSaleRequestModel saleRequestModel, RedirectAttributes model) {
        Sale sale = DTOConvertUtil.convert(saleRequestModel, Sale.class);

        this.saleRepository.saveAndFlush(sale);
    }
}
