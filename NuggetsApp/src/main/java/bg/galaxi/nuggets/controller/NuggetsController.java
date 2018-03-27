package bg.galaxi.nuggets.controller;

import bg.galaxi.nuggets.model.entity.Nugget;
import bg.galaxi.nuggets.model.response.NuggetResponseModel;
import bg.galaxi.nuggets.service.INuggetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Controller
public class NuggetsController extends BaseController {

    private final INuggetService nuggetService;

    @Autowired
    public NuggetsController(INuggetService nuggetService) {
        this.nuggetService = nuggetService;
    }

    @GetMapping("/nuggets")
    public ModelAndView nuggets() {
        return super.view(this.nuggetService.findAll(), null);
    }
}
