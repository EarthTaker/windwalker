package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.snhu.garrison.windwalker.Model.FlightOption;
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
     * @param model
     *
     * Note: Once Spring Security authenticates the user and the browser
     * responds with a GET request to /dashboard, this method is invoked to
     * render the authenticated user's home page.
     *
     * @return - View name resolved by Thymeleaf to render HTML.
     */
    @GetMapping("/dashboard")
    public String authenticatedHome(Model model) {

        model.addAttribute("page", "dashboard");

        // User is authenticated; render the home view.
        return "home";
    }

    /**
     * Method to handle basic navigation to Booking Page.
     *
     * @param model
     * @return
     */
    @GetMapping("/bookFlight")
    public String bookFlightPage(HttpSession session, Model model) {
        model.addAttribute("page", "booking");

        //Check if a flight has been selected; if not, redirect to home.
        if (session.getAttribute("selectedFlight") == null) {
            return "redirect:/";
        }
        
        //Retrieve selected flight from session and add to model for rendering.
        FlightOption flight = (FlightOption) session.getAttribute("selectedFlight");
        model.addAttribute("flight", flight);
        
        return "bookFlight";
    }

}
