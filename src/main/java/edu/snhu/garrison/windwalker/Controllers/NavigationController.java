package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // Creates application entry point mapping for SpringBoot to see the method
    @GetMapping("/")
    public String homePage(Model model) {

        // Creates an explicit connection between the active page and the html template
        // passed to the view resolver.
        model.addAttribute("page", "home");
        return "home";
    }

}
