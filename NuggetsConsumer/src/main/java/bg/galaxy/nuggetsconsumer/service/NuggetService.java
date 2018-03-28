package bg.galaxy.nuggetsconsumer.service;

import bg.galaxy.nuggetsconsumer.repository.NuggetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by George-Lenovo on 28/03/2018.
 */
@Service
@Transactional
public class NuggetService implements INuggetService{

    private final NuggetRepository nuggetRepository;

    @Autowired
    public NuggetService(NuggetRepository nuggetRepository) {
        this.nuggetRepository = nuggetRepository;
    }

    @Override
    public List<String> findByName(String username) {
      return this.nuggetRepository.
                findByName(username.replace(",", "|"));
    }
}
