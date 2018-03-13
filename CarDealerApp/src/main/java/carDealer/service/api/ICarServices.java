package carDealer.service.api;

import carDealer.model.request.AddCarRequestModel;
import carDealer.model.response.CarResponseModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface ICarServices {
    List<CarResponseModel> orderedAscendingCustomers(String make);

    <T> T findOne(Long id, Class<T> clazz);

    List<CarResponseModel> findAll();

    void addCar(AddCarRequestModel carRequestModel, RedirectAttributes model);
}
