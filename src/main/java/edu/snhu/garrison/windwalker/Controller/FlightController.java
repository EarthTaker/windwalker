package edu.snhu.garrison.windwalker.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightController {
    
    //Creates the mapping for SpringBoot to see the method
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }
}
