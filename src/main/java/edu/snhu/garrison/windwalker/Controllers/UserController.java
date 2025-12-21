package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // Creates the log out page mapping for SpringBoot to see the method
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Creates the login page mapping for SpringBoot to see the method
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Creates the register user page mapping for SpringBoot to see the method
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Creates the home page mapping for a logged in user.
    @PostMapping("/home")
    public String loggedInUserHomePage() {
        return "redirect:/";
    }

}