package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ConsumerDTO;
import com.example.service.DepartmentOfficialService;

@RestController
@RequestMapping("/officials")
public class DepartmentOfficialController {
	
	@Autowired
  private final DepartmentOfficialService departmentOfficialService;

  public DepartmentOfficialController(DepartmentOfficialService departmentOfficialService) {
    this.departmentOfficialService = departmentOfficialService;
  }
  
  
//Consumer Registration

  @PostMapping("register/consumers")
  public ResponseEntity<?> registerConsumer(@RequestBody ConsumerDTO consumerDTO) {
    // Implement logic to register new consumers
    // Generate a unique ID, validate input, save consumer details, etc.
    departmentOfficialService.registerConsumer(consumerDTO);
    return ResponseEntity.ok().build();
  }
  
  // Get ALL Consumers
  
  // Get User By ID
}

