package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

/**
 * Class controlling overall application navigation.
 */
@Controller
public class NavigationController {

    /**
     * Application Entry Point.
     */
    @GetMapping("/")
    public String homePage(Model model) {

        // Creates an explicit connection between the active page and the html template
        // passed to the view resolver.
        model.addAttribute("page", "home");
        return "home";
    }

    /**
     * Method to handle basic navigation to Login Page.
     * 
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("page", "login");
        return "login";
    }

    /**
     * Method to handle basic navigation to Register Page.
     * 
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("page", "register");
        return "register";
    }

    /**
     * Home Page for a logged-in user.
     *
     * @param session - HttpSession associated with the current request,
     * retrieved by the servlet container (Tomcat) and exposed by Spring.
     * @return - View name resolved by Thymeleaf to render HTML.
     */
    @GetMapping("/dashboard")
    public String authenticatedHome(HttpSession session, Model model) {

        // Guard: if no authenticated user exists in the session,
        // redirect the browser back to the login page.
        if (session.getAttribute("authenticatedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("page", "dashboard");

        // User is authenticated; render the home view.
        return "home";
    }

}
