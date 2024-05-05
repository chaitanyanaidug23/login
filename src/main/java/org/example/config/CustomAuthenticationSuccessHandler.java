package org.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            // Redirects to the home page after successful authentication
            response.sendRedirect("/home");
        } catch (IOException e) {
            // Handle IO exception gracefully
            // You might want to log the exception for debugging purposes
            System.err.println("Error redirecting to home page: " + e.getMessage());
        }
    }
}
