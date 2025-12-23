package edu.snhu.garrison.windwalker.Services;

import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    /**
     * Authenticates a user based on the provided username and password.
     * 
     * @param username
     * @param password
     * @return
     */
    public boolean authenticateUser(String username, String password) {
        return true;
    }
}
