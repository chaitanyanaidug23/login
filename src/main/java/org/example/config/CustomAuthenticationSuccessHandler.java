package org.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_FACULTY")) {
            response.sendRedirect("/faculty");  // Redirecting to the faculty dashboard
        } else if (roles.contains("ROLE_STUDENT")) {
            response.sendRedirect("/student");  // Redirecting to the student dashboard
        } else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");  // Redirecting to the admin dashboard
        } else {
            response.sendRedirect("/home");  // Default redirection for other roles or if no role is matched
        }
    }
}
