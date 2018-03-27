package bg.galaxi.nuggets.service;

import bg.galaxi.nuggets.model.entity.Nugget;
import bg.galaxi.nuggets.model.response.NuggetResponseModel;
import bg.galaxi.nuggets.repository.NuggetRepository;
import bg.galaxi.nuggets.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Service
@Transactional
public class NuggetService implements INuggetService {
    private final NuggetRepository nuggetRepository;

    @Autowired
    public NuggetService(NuggetRepository nuggetRepository) {
        this.nuggetRepository = nuggetRepository;
    }

    @Override
    public List<NuggetResponseModel> findAll() {
        return DTOConverter.convert(this.nuggetRepository.findAll(), NuggetResponseModel.class);
    }

    @Override
    public List<Nugget> findAllById(String[] ids) {
        StringBuilder idsSql = new StringBuilder();
        Arrays.stream(ids).forEach(n -> idsSql.append(String.format("'%s',", n)));
        return this.nuggetRepository.findAllByIds(idsSql.toString().substring(0, idsSql.length()-1));
    }
}
