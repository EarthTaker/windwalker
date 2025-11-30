package edu.snhu.garrison.windwalker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class FlightHomeController {

    // Creates application entry point mapping for SpringBoot to see the method
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    // Returns the results page when the form is submitted, requires parameters be submitted from the form.
    @GetMapping("/results")
    public String resultsPage(@RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("departureDate") String departureDate,
            @RequestParam(value = "returnDate", required = false) String returnDate,
            @RequestParam("tripType") String tripType,
            @RequestParam("passengers") int passengers,
            @RequestParam("seatClass") String seatClass,
            Model model) {

        //Grab the parameters and add them to Spring's model to be used by Thymeleaf in the results.html page
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("returnDate", returnDate);
        model.addAttribute("tripType", tripType);
        model.addAttribute("passengers", passengers);
        model.addAttribute("seatClass", seatClass);
    
        // This tells Thymeleaf what template to render. The controller hands the view name to Spring, 
        // Spring uses a ViewResolver to chose Thymeleaf as the template engine, Thymeleaf then renders the results.html page.
        return "results";
    }
}
