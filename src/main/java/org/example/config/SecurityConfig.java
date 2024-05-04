package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("alice").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disabling CSRF protection for demo purposes
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/admindashboard.html").permitAll()  // Allow access to static resources and admin dashboard HTML
                .antMatchers("/admin/**").hasRole("ADMIN")  // Restrict access to admin endpoints to users with the 'ADMIN' role
                .anyRequest().authenticated()  // Require authentication for any other requests
                .and()
                .formLogin()
                .loginPage("/login")  // Custom login page
                .defaultSuccessUrl("/home", true)  // Redirect to the home page after successful login
                .permitAll()  // Allow everyone to see the login page
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  // Redirect to login page with logout message
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");  // Custom access denied page

        // Session management
        http.sessionManagement()
                .invalidSessionUrl("/login?invalid")  // Redirect to login page when session is invalid
                .maximumSessions(1).expiredUrl("/login?expired");  // Handling session expiration
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Password encoder for encoding and verifying passwords
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();  // Custom handler for successful authentication
    }
}
