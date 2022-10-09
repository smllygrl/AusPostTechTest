package com.auspost.AddressesAPI;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// CODE COPIED VERBATIM FROM BELOW
	// https://www.danvega.dev/blog/2022/09/06/spring-security-jwt/
	
	// NOTE
	// I tried to develop an @Overide for the below so that ONLY the post request requests login details
	// I fixed my issue by adding a @PreAuthorize("permitAll()") to my GET endpoints in my controller
	// This is obviously not best practice and given more time ALL security config would be handled in this file

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests( auth -> auth
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    

    
    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
        		// NOTE
        		// In production, username and password would NOT be visible in source code.
        		// Environment variables could be used, as well as secure and centralised secrets control through DevOps
                User.withUsername("user")
                        .password("{noop}password")
                        .authorities("read")
                        .build()
        );
    }
    

}