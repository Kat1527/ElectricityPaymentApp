package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.payloads.LoginResponseDTO;
import com.example.repository.UserRepository;

public class AuthenticationService {
	
	@Autowired
	UserRepository userRepository;
}
//	
//	 public LoginResponseDTO loginUser(String username, String password) {
//
////	        try {
////	            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
////
////	            String token = tokenService.generateJwt(auth);
////
////	            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
////
////	        } catch (AuthenticationException e) {
////	            return new LoginResponseDTO(null, "");
////	        }
////	    }
//
//}
