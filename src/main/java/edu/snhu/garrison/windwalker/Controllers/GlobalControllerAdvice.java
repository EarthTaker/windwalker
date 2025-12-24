package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.snhu.garrison.windwalker.Model.User;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Method: Before any controller is called, call to the Global Controller,
     * attempt to get the user from the session and add it to the model.
     * 
     * @ModelAttribute("user") - Establishes the user object as a Key-Value pair,
     * using the method return value as the value. Stored within the model for use
     * in the view.
     * 
     * @param session - HTTP session object
     * @return - User object
     */
    @ModelAttribute("user")
    public User addUserToModel(HttpSession session) {

        return (User) session.getAttribute("user");
    }
}
