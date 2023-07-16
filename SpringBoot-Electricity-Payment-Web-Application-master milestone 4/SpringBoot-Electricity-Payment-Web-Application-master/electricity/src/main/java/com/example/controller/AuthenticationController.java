package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.payloads.JwtRequest;
import com.example.payloads.JwtResponse;
import com.example.repository.UserRepository;
import com.example.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        String token = this.doAuthenticate(request.getEmail(), request.getPassword());


    //    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());	
 //       String token = this.helper.generateToken(userDetails);
        
         
   //     User user = userRepository.findByUsername(userDetails.getUsername())
     //           .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));


        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(request.getEmail()).roles(request.getRole()).build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        
           if( manager.authenticate(authentication).isAuthenticated())
        	   return this.helper.generateToken(email);          
        
           else
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
	
}
