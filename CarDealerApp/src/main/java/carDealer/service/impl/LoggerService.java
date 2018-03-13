package carDealer.service.impl;

import carDealer.model.entity.Logger;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.response.LoggerResponseModel;
import carDealer.repository.LoggerRepository;
import carDealer.repository.UserRepository;
import carDealer.service.api.ILoggerService;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
@Service
@Transactional
public class LoggerService implements ILoggerService {

    private final LoggerRepository loggerRepository;

    private final UserRepository userRepository;

    @Autowired
    public LoggerService(LoggerRepository loggerRepository, UserRepository userRepository) {
        this.loggerRepository = loggerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addLog(ModelMap modelMap) {
        String username = (String) modelMap.get("user");
        String operation = (String) modelMap.get("operation");
        String modifiedTable = (String) modelMap.get("modifiedTable");

        this.loggerRepository.saveAndFlush(
                new Logger(
                        this.userRepository.findByUsername(username),
                        Operation.valueOf(operation.toUpperCase()),
                        TableEnum.valueOf(modifiedTable.toUpperCase()),
                        new Date()
                )
        );
    }

    @Override
    public List<LoggerResponseModel> findAll() {
        return DTOConvertUtil.convert(this.loggerRepository.findAll(), LoggerResponseModel.class);
    }

    @Override
    public void removeAll() {
        this.loggerRepository.deleteAll();
    }

    @Override
    public List<LoggerResponseModel> search(String search, String username) throws NullPointerException {
        if (search == null) return null;
        return DTOConvertUtil.convert(this.userRepository.findByUsername(username).getLogs(), LoggerResponseModel.class);
    }
}
