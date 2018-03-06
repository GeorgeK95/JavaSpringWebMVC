package carDealer.service;

import carDealer.model.entity.Car;
import carDealer.model.entity.Part;
import carDealer.model.request.AddCarRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.repository.CarsRepository;
import carDealer.repository.PartRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class CarServices {
    private final CarsRepository repository;
    private final PartRepository partRepository;

    @Autowired
    public CarServices(CarsRepository repository, PartRepository partRepository) {
        this.repository = repository;
        this.partRepository = partRepository;
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

    public void addCar(AddCarRequestModel carRequestModel, RedirectAttributes model) {
        Car car = DTOConvertUtil.convert(carRequestModel, Car.class);

        Set<Part> carParts = DTOConvertUtil.convert(this.partRepository.findPartsById(carRequestModel.getSelectedParts()), Part.class)
                .stream()
                .collect(Collectors.toSet());
        car.setParts(carParts);

        this.setNotification(car, model, "car_add_notification");

        this.repository.saveAndFlush(car);
/*
        carParts.forEach(cp -> cp.setCars(new HashSet<Car>(cp.getCars()) {{
            add(car);
        }}));

        this.partRepository.save(carParts);*/
    }

    private void setNotification(Car car, RedirectAttributes model, String key) {
        String message = String.format("Car %s added successfully.", car.getMake().concat(car.getModel()));
        model.addFlashAttribute(key, message);
    }

}
