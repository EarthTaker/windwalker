package edu.snhu.garrison.windwalker.Services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.snhu.garrison.windwalker.Model.User;

/**
 * Integration tests for UserAuthenticationService.
 * Uses real XML file and Spring context - tests actual behavior end-to-end.
 */
@SpringBootTest
public class UserAuthenticationServiceTest {

    @Autowired
    private UserAuthenticationService userAuthService;

    /**
     * Test that a new user with valid data can be registered successfully.
     * This is an integration test - it actually writes to users.xml.
     */
    @Test
    void registerUser_shouldRegister_whenValid() {
        // Create a new user with unique email and username
        User newUser = new User(
            "testUser" + System.currentTimeMillis(), // Unique username
            "Test",
            "User", 
            "test" + System.currentTimeMillis() + "@example.com", // Unique email
            "555-0123",
            "password123"
        );

        // Attempt to register - should succeed
        boolean result = userAuthService.registerUser(newUser);

        // Assert registration succeeded
        assertTrue(result, "Registration should succeed with valid unique user data");
    }

    /**
     * Test that registration fails when username and email already exist.
     */
    @Test
    void registerUser_shouldNOTRegister_whenUsernameAndEmailExists() {
        // Use the existing "admin" user from users.xml
        User duplicateUser = new User(
            "admin",           // Existing username
            "Admin",
            "User",
            "admin@test.com", // Existing email
            "555-0000",
            "password"
        );

        // Attempt to register - should fail
        boolean result = userAuthService.registerUser(duplicateUser);

        // Assert registration failed
        assertFalse(result, "Registration should fail when username and email already exist");
    }
}
