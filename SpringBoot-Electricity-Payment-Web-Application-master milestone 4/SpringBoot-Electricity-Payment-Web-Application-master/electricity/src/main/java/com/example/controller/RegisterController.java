package com.example.controller;

import java.util.HashSet;
import java.util.Set;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Admin;
import com.example.entity.Consumer;
import com.example.entity.Official;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.payloads.RegisterRequest;
import com.example.repository.AdminRepository;
import com.example.repository.ConsumerRepository;
import com.example.repository.OfficialRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class RegisterController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	OfficialRepository officialRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        // Validate registration request

        // Create User entity
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Set user roles based on the registration request
////        Set<Role> roles = new HashSet<>();
////        if (registerRequest.getRoles().contains("ADMIN")) {
////            roles.add(roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new RoleNotFoundException("Admin role not found")));
////        }
////        if (registerRequest.getRoles().contains("OFFICIAL")) {
////            roles.add(roleRepository.findByAuthority("OFFICIAL").orElseThrow(() -> new RoleNotFoundException("Official role not found")));
////        }
////        if (registerRequest.getRoles().contains("CONSUMER")) {
////            roles.add(roleRepository.findByAuthority("CONSUMER").orElseThrow(() -> new RoleNotFoundException("Consumer role not found")));
////        }
////        user.setRoles(roles);
//
//        // Save the user entity to the corresponding repository based on their role
//        if (roles.contains(roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new RoleNotFoundException("Admin role not found")))) {
//            Admin admin = new Admin();
//            admin.setUser(user);
//            adminRepository.save(admin);
//            
//        } else if (roles.contains(roleRepository.findByAuthority("OFFICIAL").orElseThrow(() -> new RoleNotFoundException("Official role not found")))) {
//            Official official = new Official();
//            official.setUser(user);
//            officialRepository.save(official);
//        } else if (roles.contains(roleRepository.findByAuthority("CONSUMER").orElseThrow(() -> new RoleNotFoundException("Consumer role not found")))) {
//            Consumer consumer = new Consumer();
//            consumer.setUser(user);
//            consumerRepository.save(consumer);
//        }

        return ResponseEntity.ok("User registered successfully");
    }
}


