package bg.galaxy.nuggetsconsumer.service;

import java.util.List;

/**
 * Created by George-Lenovo on 28/03/2018.
 */
public interface INuggetService {
    List<String> findByName(String name);
}
