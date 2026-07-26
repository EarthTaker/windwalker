package edu.snhu.garrison.windwalker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration class to set up authentication and authorization
 * rules. Syntax Explanation:
 *
 * @Configuration - Defines a class that produces beans managed by the Spring
 * Container.
 */
@Configuration
public class SecurityConfig {

    /**
     * Security Filter Chain Bean to configure HTTP security settings.
     *
     * Syntax Explanation:
     *
     * @Bean - Indicates that a method produces a bean to be managed by Spring.
     * @param http - HttpSecurity object to configure web based security for
     * specific http requests. It is created by Spring Security, handed to
     * Spring Container, and then injected into this method. auth - Temporary
     * authorization object created by Spring Security to define access rules
     * for different endpoints. Uses Lambda syntax to target its class methods.
     * @return - SecurityFilterChain object (sits between the Tomcat server and
     * the DispatcherServlet), creating a layer of security for HTTP requests.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                //PUBLIC ROUTES - No authentication required
                //Navigation endpoints
                .requestMatchers("/", "/login", "/register").permitAll()
                //User API endpoints
                .requestMatchers("/api/auth/register").permitAll()
                //Flight API endpoints
                .requestMatchers("/api/flights/search").permitAll()
                //H2 Console - Allows access to H2 database console for development.
                .requestMatchers("/h2-console/**").permitAll()
                //Static Resources - Ensures CSS, JS, and Image resources are publicly accessible.
                .requestMatchers("/css/**", "/js/**").permitAll()
                //PROTECTED ROUTES - Require authentication
                .requestMatchers("/api/booking/**", "/dashboard", "/bookFlight").authenticated()
                //CATCH-ALL - All other requests require authentication
                .anyRequest().authenticated()
        )
                //Login Configuration
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error"))
                //Logout Configuration
                .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll());

        //Disable CSRF protection for H2 Console and REST API endpoints
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
                .ignoringRequestMatchers("/api/**")
        );

        //Allow X-Frame-Options for H2 Console (console uses iframes).
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        //Returns a list of security filters that will be applied to each HTTP request.
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
