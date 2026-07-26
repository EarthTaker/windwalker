package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.snhu.garrison.windwalker.Model.RegisterRequest;
import edu.snhu.garrison.windwalker.Model.User;
import edu.snhu.garrison.windwalker.Services.UserAuthenticationService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class UserAPIController {

    // Automatically injects the UserAuthenticationService bean into the controller
    // at runtime.
    @Autowired
    private UserAuthenticationService authService;

    /**
     * * Method - Logs out a user and destroys session.
     *
     * @param session - Require the session to invalidate its state.
     * @return - Redirects to the login page.
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {

        return ResponseEntity.ok().build();
    }

    /**
     * Method - Allows user to register.
     *
     * @param req - RequestBody - Builds LoginRequest DTO using inbound HTTP
     * Request
     * @return - ResponseEntity - Wrapper for HTTP Response
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest req) {

        //Generate a new User from register request DTO.
        User user = new User(req.getUsername(), req.getFirstName(), req.getLastName(), req.getEmail(),
                req.getPhone(), req.getPassword());

        //Register User
        if (authService.registerUser(user)) {

            //Return ResponseEntity with 201 HTTP Code (Created).
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        //Return ResponseEntity with 409 HTTP Code (Conflict). 
        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }
}
