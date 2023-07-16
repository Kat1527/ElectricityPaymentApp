package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OfficialDTO;
import com.example.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }
  
  // Department Official Registration

  @PostMapping("register/officials")
  public ResponseEntity<?> registerOfficial(@RequestBody OfficialDTO officialDTO) {
    // Implement logic to register new officials
    // Validate input, save official details, etc.
    adminService.registerOfficial(officialDTO);
    return ResponseEntity.ok().build();
  }
  
  // Get ALL Department Official
  
  // Get ALL Consumers
}
