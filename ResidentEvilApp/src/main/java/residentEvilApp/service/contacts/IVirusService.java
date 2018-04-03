package residentEvilApp.service.contacts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.model.entity.Virus;
import residentEvilApp.model.request.AddVirusRequestModel;
import residentEvilApp.model.request.EditVirusRequestModel;
import residentEvilApp.model.response.VirusResponseModel;

import java.util.List;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
public interface IVirusService {
    List<VirusResponseModel> findAll();

    void addVirus(AddVirusRequestModel requestModel);

    <T> T findOne(Long id, Class<T> clazz);

    void editVirus(Long id, EditVirusRequestModel requestModel);

    void deleteVirus(Long id);

    @Transactional
    String findAllMapViruses();

    Page<Virus> listAllByPage(Pageable pageable);

    default long getTotalPages() {
        return getTotalPages(12);
    }

    long getTotalPages(int size);
}
