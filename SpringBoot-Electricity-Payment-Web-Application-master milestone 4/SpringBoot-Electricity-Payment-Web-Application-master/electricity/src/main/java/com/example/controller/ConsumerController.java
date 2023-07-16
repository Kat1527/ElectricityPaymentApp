package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PropertyDTO;
import com.example.entity.Bill;
import com.example.entity.Property;
import com.example.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
  private final ConsumerService consumerService;

  public ConsumerController(ConsumerService consumerService) {
    this.consumerService = consumerService;
  }
  
  // ADD a property
  
  @PostMapping("add/property/{consumerId}")
  public ResponseEntity<?> addProperty(@PathVariable Long consumerId, @RequestBody PropertyDTO propertyDTO) {
    // Implement logic to register new officials
    // Validate input, save official details, etc.
	  
      consumerService.addUserProperty(consumerId, propertyDTO);

	  //consumerService.registerOfficial(officialDTO);
    return ResponseEntity.ok().build();
  }
  
  // Get Property BY ID
  
  // Update a property
  
  // update Profile Details - Basic info
  
  // View ALL properties
  @GetMapping("/allproperties/{consumerId}")
  public ResponseEntity<List<Property>> getAllPropertiesByConsumerId(@PathVariable Long consumerId) {
    // Implement logic to fetch all properties for a specific consumer
    List<Property> properties = consumerService.getPropertiesByConsumerId(consumerId);
    return ResponseEntity.ok(properties);
  }
  
  
  // View Bills

  @GetMapping("/bills/{consumerId}")
  public ResponseEntity<List<List<Bill>>> getBillsByConsumerId(@PathVariable Long consumerId) {
    // Implement logic to fetch bills for a specific consumer
    List<List<Bill>> bills = consumerService.getBillsByConsumerId(consumerId);
    return ResponseEntity.ok(bills);
  }
}
