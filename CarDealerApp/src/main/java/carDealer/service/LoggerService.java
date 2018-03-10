package carDealer.service;

import carDealer.model.entity.Logger;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.response.LoggerResponseModel;
import carDealer.repository.LoggerRepository;
import carDealer.repository.UserRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
@Service
@Transactional
public class LoggerService {

    private final LoggerRepository loggerRepository;

    private final UserRepository userRepository;

    @Autowired
    public LoggerService(LoggerRepository loggerRepository, UserRepository userRepository) {
        this.loggerRepository = loggerRepository;
        this.userRepository = userRepository;
    }

    public void addLog(ModelMap modelMap) {
        String user = (String) modelMap.get("user");
        String operation = (String) modelMap.get("operation");
        String modifiedTable = (String) modelMap.get("modifiedTable");

        this.loggerRepository.saveAndFlush(
                new Logger(
                        this.userRepository.findOne(3L),
                        Operation.valueOf(operation.toUpperCase()),
                        TableEnum.valueOf(modifiedTable.toUpperCase()),
                        new Date()
                )
        );
    }

    public static ModelAndView constructModelAndView(String redirectTo,
                                                     String user,
                                                     String operation,
                                                     String table) {
        ModelAndView modelAndView = new ModelAndView(redirectTo);

        modelAndView.addObject("user", user);
        modelAndView.addObject("operation", operation);
        modelAndView.addObject("modifiedTable", table);

        return modelAndView;
    }

    public List<LoggerResponseModel> findAll() {
        return DTOConvertUtil.convert(this.loggerRepository.findAll(), LoggerResponseModel.class);
    }

    public void removeAll() {
        this.loggerRepository.deleteAll();
    }

    public List<LoggerResponseModel> search(String search, String username) throws NullPointerException {
        if (search == null) return null;
        return DTOConvertUtil.convert(this.userRepository.findByUsername(username).getLogs(), LoggerResponseModel.class);
    }
}
