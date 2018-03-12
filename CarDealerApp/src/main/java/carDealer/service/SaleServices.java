package carDealer.service;

import carDealer.model.entity.Car;
import carDealer.model.entity.Customer;
import carDealer.model.entity.Sale;
import carDealer.model.request.AddSaleReviewRequestModel;
import carDealer.model.response.SaleFinalizeResponseModel;
import carDealer.model.response.SaleInfoResponseModel;
import carDealer.model.response.SaleResponseModel;
import carDealer.repository.CarsRepository;
import carDealer.repository.CustomerRepository;
import carDealer.repository.SaleRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class SaleServices {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarsRepository carsRepository;

    @Autowired
    public SaleServices(SaleRepository saleRepository, CustomerRepository customerRepository, CarsRepository carsRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carsRepository = carsRepository;
    }

    public List<SaleResponseModel> findAllDiscounted() {
        return DTOConvertUtil.convert(this.saleRepository.findAllDiscounted(), SaleResponseModel.class);
    }

    public List<SaleResponseModel> findAllDiscountedByPercent(Double percent) {
        return DTOConvertUtil.convert(this.saleRepository.findAllDiscountedByPercent(percent), SaleResponseModel.class);
    }

    public List<SaleResponseModel> totalSalesByCustomer(Long id) {
        return DTOConvertUtil.convert(this.saleRepository.totalSalesByCustomer(id), SaleResponseModel.class);
    }

    public List<SaleResponseModel> findAll() {
        return DTOConvertUtil.convert(this.saleRepository.findAll(), SaleResponseModel.class);
    }

    public void addSale(AddSaleReviewRequestModel saleRequestModel) {
        Car car = this.carsRepository.findFirstByMakeAndModel(
                saleRequestModel.getCarMakeModel().split("\\s")[0],
                saleRequestModel.getCarMakeModel().split("\\s")[1]
        );

        Customer customer = this.customerRepository.findByName(saleRequestModel.getCustomerName());

        Sale sale = new Sale(saleRequestModel.getDiscount(), car, customer);

        this.saleRepository.saveAndFlush(sale);
    }

    public SaleInfoResponseModel constructSaleInfoResponseModelObject(Model model) {
        SaleInfoResponseModel saleInfoResponseModel = (SaleInfoResponseModel) model.asMap().get("sale");
        SaleFinalizeResponseModel finalizeSaleResponseModel = new SaleFinalizeResponseModel();
        finalizeSaleResponseModel.setCar(saleInfoResponseModel.getCar());
        finalizeSaleResponseModel.setCustomer(saleInfoResponseModel.getCustomer());
        finalizeSaleResponseModel.setDiscount(saleInfoResponseModel.getDiscount() * 100);
        return saleInfoResponseModel;
    }
}
