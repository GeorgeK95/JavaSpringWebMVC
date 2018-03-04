package carDealer.service;

import carDealer.model.Car;
import carDealer.model.response.CarResponseModel;
import carDealer.repository.CarsRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class CarServices {
    private CarsRepository repository;

    @Autowired
    public CarServices(CarsRepository repository) {
        this.repository = repository;
    }

    public List<CarResponseModel> orderedAscendingCustomers(String make) {
        return DTOConvertUtil.convert(this.repository.carsFromMake(make), CarResponseModel.class);
    }

    public CarResponseModel findById(Long id) {
        Car one = this.repository.findOne(id);
        return DTOConvertUtil.convert(one, CarResponseModel.class);
    }

    public List<CarResponseModel> findAll() {
        return DTOConvertUtil.convert(this.repository.findAll(), CarResponseModel.class);
    }
}
