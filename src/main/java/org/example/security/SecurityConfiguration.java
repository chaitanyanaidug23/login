package org.example.security;

import org.example.config.CustomAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        log.debug("AuthenticationManager configured with userDetailsService and passwordEncoder.");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.debug("AuthenticationManager bean created.");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/error", "/static/**").permitAll() // Static resources and login page accessible to all
                .antMatchers("/faculty/**").hasAuthority("ROLE_FACULTY")  // Only faculty can access faculty pages
                .antMatchers("/student/**").hasAuthority("ROLE_STUDENT")  // Only students can access student pages
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")      // Only admins can access admin pages
                .anyRequest().authenticated()  // All other requests need to be authenticated
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler) // Use your custom success handler
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  // After logout redirect to login page
                .permitAll();
    }

}
