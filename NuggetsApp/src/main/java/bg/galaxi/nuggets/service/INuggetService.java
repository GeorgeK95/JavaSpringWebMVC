package bg.galaxi.nuggets.service;

import bg.galaxi.nuggets.model.entity.Nugget;
import bg.galaxi.nuggets.model.response.NuggetResponseModel;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
public interface INuggetService {
    List<NuggetResponseModel> findAll();

    List<Nugget> findAllById(String[] ids);
}
