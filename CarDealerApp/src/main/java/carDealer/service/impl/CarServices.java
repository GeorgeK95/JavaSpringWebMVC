package carDealer.service.impl;

import carDealer.model.entity.Car;
import carDealer.model.entity.Part;
import carDealer.model.request.AddCarRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.repository.CarsRepository;
import carDealer.repository.PartRepository;
import carDealer.service.api.ICarServices;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class CarServices implements ICarServices {
    private final CarsRepository repository;
    private final PartRepository partRepository;

    @Autowired
    public CarServices(CarsRepository repository, PartRepository partRepository) {
        this.repository = repository;
        this.partRepository = partRepository;
    }

    @Override
    public List<CarResponseModel> orderedAscendingCustomers(String make) {
        return DTOConvertUtil.convert(this.repository.carsFromMake(make), CarResponseModel.class);
    }

    @Override
    public <T> T findOne(Long id, Class<T> clazz) {
        Car one = this.repository.findOne(id);
        return DTOConvertUtil.convert(one, clazz);
    }

    @Override
    public List<CarResponseModel> findAll() {
        return DTOConvertUtil.convert(this.repository.findAll(), CarResponseModel.class);
    }

    @Override
    public void addCar(AddCarRequestModel carRequestModel, RedirectAttributes model) {
        Car car = DTOConvertUtil.convert(carRequestModel, Car.class);

        Set<Part> carParts = new HashSet<>(DTOConvertUtil.convert(this.partRepository.findPartsById(
                carRequestModel.getSelectedParts()), Part.class)
        );
        car.setParts(carParts);

        this.setNotification(car, model, "car_add_notification");

        this.repository.saveAndFlush(car);
    }

    private void setNotification(Car car, RedirectAttributes model, String key) {
        String message = String.format("Car %s added successfully.", car.getMake().concat(car.getModel()));
        model.addFlashAttribute(key, message);
    }

}
