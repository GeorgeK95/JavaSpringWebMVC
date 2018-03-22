package residentEvilApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "error/unauthorized";
    }

}
