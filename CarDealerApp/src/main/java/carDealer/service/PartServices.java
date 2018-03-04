package carDealer.service;

import carDealer.model.response.PartResponseModel;
import carDealer.repository.PartRepository;
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
public class PartServices {

    private final PartRepository repository;

    @Autowired
    public PartServices(PartRepository repository) {
        this.repository = repository;
    }

    public List<PartResponseModel> getPartsByCarId(Long id) {
        return DTOConvertUtil.convert(this.repository.findCarPartsById(id), PartResponseModel.class);
    }
}
