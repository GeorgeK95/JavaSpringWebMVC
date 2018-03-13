package carDealer.service.api;

import carDealer.model.request.AddPartRequestModel;
import carDealer.model.response.PartResponseModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
public interface IPartServices {
    List<PartResponseModel> getPartsByCarId(Long id);

    PartResponseModel findOne(Long id);

    List<PartResponseModel> findAll();

    void addPart(AddPartRequestModel requestModel, RedirectAttributes model);

    void editPart(Long id, AddPartRequestModel requestModel, RedirectAttributes model);

    void deletePart(Long id, AddPartRequestModel requestModel, RedirectAttributes model);
}
