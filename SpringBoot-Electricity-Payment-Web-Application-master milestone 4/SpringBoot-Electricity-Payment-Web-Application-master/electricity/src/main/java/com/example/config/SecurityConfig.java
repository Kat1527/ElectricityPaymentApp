package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JWTAthenticationEntryPoint;
import com.example.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JWTAthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	 http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/v3/**").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/official/**").hasAnyRole("ADMIN","OFFICIAL")
                .requestMatchers("/consumer/**").hasAnyRole("ADMIN","OFFICIAL", "CONSUMER")
                .requestMatchers("/test").authenticated()
                .requestMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                 
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
               
    	 http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
    
    
   
	
	
//	 @Bean
//	    public AuthenticationProvider authenticationProvider(){
//	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//	        authenticationProvider.setUserDetailsService(userDetailsService());
//	        authenticationProvider.setPasswordEncoder(passwordEncoder());
//	        return authenticationProvider;
//	    }
	 
	 
	 
	 
	
	 
	}
    
   


