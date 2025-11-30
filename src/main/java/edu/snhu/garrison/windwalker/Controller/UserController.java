package edu.snhu.garrison.windwalker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // Creates the login page mapping for SpringBoot to see the method
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Creates the home page mapping for a logged in user.
    @PostMapping("/home")
    public String loggedInUserHomePage() {
        return "redirect:/";
    }

}