package org.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_FACULTY")) {
            response.sendRedirect("/faculty");  // Redirect to the faculty controller path
        } else if (roles.contains("ROLE_STUDENT")) {
            response.sendRedirect("/student");  // Adjust if there is a student path
        } else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");  // Adjust if there is an admin path
        } else {
            response.sendRedirect("/home");  // Adjust for a general home or landing page if applicable
        }
    }
}
