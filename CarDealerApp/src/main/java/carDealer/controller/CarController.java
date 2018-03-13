package carDealer.controller;

import carDealer.annotations.LoggedAction;
import carDealer.annotations.PreAuthenticated;
import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;
import carDealer.model.request.AddCarRequestModel;
import carDealer.model.request.UserRegisterRequestModel;
import carDealer.model.response.CarResponseModel;
import carDealer.model.response.PartResponseModel;
import carDealer.service.api.ICarServices;
import carDealer.service.api.ILoggerService;
import carDealer.service.api.IPartServices;
import carDealer.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

import static carDealer.utils.Constants.LOGIN_MODEL;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Controller
@RequestMapping("/cars/")
public class CarController {

    private final carDealer.service.api.ICarServices ICarServices;

    private final carDealer.service.api.IPartServices IPartServices;

    private final carDealer.service.api.IUserService IUserService;

    @Autowired
    public CarController(ICarServices ICarServices, IPartServices IPartServices, IUserService IUserService) {
        this.ICarServices = ICarServices;
        this.IPartServices = IPartServices;
        this.IUserService = IUserService;
    }

    @GetMapping("all")
    public String allCars(Model model) {
        List<CarResponseModel> allCars = this.ICarServices.findAll();

        model.addAttribute("view", "car/carsTable");
        model.addAttribute("cars", allCars);

        return "base-layout";
    }

    @GetMapping("{make}")
    public String carsFromMake(@PathVariable String make, Model model) {
        List<CarResponseModel> cars = this.ICarServices.orderedAscendingCustomers(make);

        model.addAttribute("cars", cars);
        model.addAttribute("view", "car/carsTable");

        return "base-layout";
    }

    @GetMapping("{id}/parts")
    public String carsWithParts(@PathVariable Long id, Model model) {
        CarResponseModel car = this.ICarServices.findOne(id, CarResponseModel.class);
        List<PartResponseModel> partsByCarId = this.IPartServices.getPartsByCarId(id);

        model.addAttribute("car", car);
        model.addAttribute("parts", partsByCarId);
        model.addAttribute("view", "part/partsTable");

        return "base-layout";
    }


    @GetMapping("add")
    @PreAuthenticated(loggedInUserRequirement = true)
    public String addCar(Model model) {
        model.addAttribute("view", "car/add");
        model.addAttribute("parts", this.IPartServices.findAll());

        return "base-layout";
    }

    @PostMapping("add")
    @LoggedAction
    @PreAuthenticated(loggedInUserRequirement = true)
    public ModelAndView addCarProcess(RedirectAttributes model, AddCarRequestModel carRequestModel,
                                      HttpSession session) {
        this.ICarServices.addCar(carRequestModel, model);

        UserRegisterRequestModel userModel = (UserRegisterRequestModel) session.getAttribute(LOGIN_MODEL);

        return ILoggerService.constructModelAndView(
                "redirect:/cars/add",
                userModel.getUsername(),
                Operation.ADD.toString(),
                TableEnum.CAR.toString()
        );
    }
}
