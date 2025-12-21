package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // Creates application entry point mapping for SpringBoot to see the method
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

}
