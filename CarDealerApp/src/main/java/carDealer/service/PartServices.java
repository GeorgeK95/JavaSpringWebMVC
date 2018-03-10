package carDealer.service;

import carDealer.model.entity.Part;
import carDealer.model.request.AddPartRequestModel;
import carDealer.model.response.PartResponseModel;
import carDealer.repository.PartRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class PartServices {

    private final PartRepository repository;

    @Autowired
    public PartServices(PartRepository repository) {
        this.repository = repository;
    }

    public List<PartResponseModel> getPartsByCarId(Long id) {
        return DTOConvertUtil.convert(this.repository.findCarPartsById(id), PartResponseModel.class);
    }

    public PartResponseModel findOne(Long id) {
        return DTOConvertUtil.convert(this.repository.findOne(id), PartResponseModel.class);
    }

    public List<PartResponseModel> findAll() {
        return DTOConvertUtil.convert(this.repository.findAll(), PartResponseModel.class);
    }

    public void addPart(AddPartRequestModel requestModel, RedirectAttributes model) {
        Part part = DTOConvertUtil.convert(requestModel, Part.class);
        part.setId(null);
        if (requestModel.getQuantity() == null) part.setQuantity(1L);


        this.repository.saveAndFlush(part);

        String message = String.format("Part %s added successfully.", part.getName());
        model.addFlashAttribute("part_add_notification", message);
    }

    public void editPart(Long id, AddPartRequestModel requestModel, RedirectAttributes model) {
        Part part = this.repository.findOne(id);

        String message = String.format("Part %s edited successfully.", part.getName());
        model.addFlashAttribute("part_edit_notification", message);

        this.editPart(part, requestModel);

        this.repository.saveAndFlush(part);
    }

    public void deletePart(Long id, AddPartRequestModel requestModel, RedirectAttributes model) {
        Part part = this.repository.findOne(id);

        String message = String.format("Part %s deleted successfully.", part.getName());
        model.addFlashAttribute("part_delete_notification", message);

        this.repository.delete(id);
    }

    private void editPart(Part part, AddPartRequestModel requestModel) {
        part.setPrice(requestModel.getPrice());
        part.setQuantity(requestModel.getQuantity());
    }
}
