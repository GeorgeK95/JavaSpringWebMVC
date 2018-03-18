package residentEvilApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import residentEvilApp.service.VirusService;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
@Controller
public class MapController {

    private final VirusService virusService;

    @Autowired
    public MapController(VirusService virusService) {
        this.virusService = virusService;
    }


    @GetMapping("/map")
    public String accessMap(Model model) {
        String geoJson = this.virusService.findAllMapViruses();

        model.addAttribute("geoJson", geoJson);
        model.addAttribute("view", "map/map");

        return "map/map";
    }
}
