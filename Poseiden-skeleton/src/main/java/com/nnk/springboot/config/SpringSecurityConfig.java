package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Security config of the application
 * Manages the security chain
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Security chain to handle user rights and redirects as well as sessions
     * @param http _
     * @param introspector _
     * @return security chain
     * @throws Exception _
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN");
            auth.requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole("USER");
            auth.anyRequest().authenticated();
            }).formLogin(f -> f.defaultSuccessUrl("/bidList/list", true)) //default page
              .exceptionHandling(e -> e.accessDeniedPage("/app/error"))             // error page
              .logout(logout -> logout
                      .logoutUrl("/app-logout") // logout url
                      .logoutSuccessUrl("/")    // redirect after logout url
                      .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES)))) // clear cookies after logout
              .sessionManagement(session -> {
                  session.maximumSessions(1).maxSessionsPreventsLogin(true); // Only 1 session max
                  session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession);
                  session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
              })
              .csrf(Customizer.withDefaults()) // Enable CSRF protection
              .cors(Customizer.withDefaults()) // Enable CORS protection
              .build();
    }

    /**
     * Defines the authentication manage for the application
     * @param http _
     * @param encoder password encoder
     * @return authentication manager
     * @throws Exception _
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder encoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // Set the password encoder of the user details service
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(encoder);

        return authenticationManagerBuilder.build();
    }

    /**
     * Password encoder
     * @return encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
