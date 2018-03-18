package residentEvilApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.model.entity.Capital;
import residentEvilApp.repository.CapitalRepository;
import residentEvilApp.service.contacts.ICapitalService;

import java.util.List;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
@Transactional
@Service
public class CapitalService implements ICapitalService {

    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalService(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }


    @Override
    public List<Capital> findAll() {
        return this.capitalRepository.findAll();
    }
}
