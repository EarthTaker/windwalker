package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.snhu.garrison.windwalker.Model.User;
import edu.snhu.garrison.windwalker.Services.UserAuthenticationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    // Automatically injects the UserAuthenticationService bean into the controller
    // at runtime.
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * 
     * @param session - Require the session to invalidate its state.
     * @return - Redirects to the login page.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Clear all session data
        session.invalidate();

        return "redirect:/";
    }

    // Creates the login page mapping for SpringBoot to see the method
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("page", "login");
        return "login";
    }

    // Creates the register user page mapping for SpringBoot to see the method
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("page", "register");
        return "register";
    }

    /**
     * Logs in a user and redirects to the home page if successful.
     * 
     * @param username
     * @param password
     * @param session  - Creates an HTTP session for the user's credentials and
     *                 logged in state.
     * @param model    - Adds the user object to the model for use in the view.
     * @return
     */
    @PostMapping("/home")
    public String loggedInUserHomePage(@RequestParam() String username, @RequestParam() String password,
            HttpSession session, Model model) {

        // Create a new user object and set the username and password.
        User user = new User(username, password);

        // Authenticate the user.
        if (userAuthenticationService.authenticateUser(user)) {

            // Store the user object in the session.
            session.setAttribute("user", user);

            return "redirect:/user/home";
        }

        return "redirect:/user/login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("page", "home");
        return "home";
    }

}