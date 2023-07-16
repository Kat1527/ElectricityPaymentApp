package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	  private PaymentService paymentService;

	  @GetMapping("/{billId}")
	  public ResponseEntity<String> makePayment(@PathVariable Long billId, @RequestParam double paymentAmount) {
	    
		  try {
	            boolean paymentProcessed = paymentService.makePayment(billId, paymentAmount);

	            if (paymentProcessed) {
	                return ResponseEntity.ok("Payment successful. Bill ID: " + billId);
	            } else {
	                return ResponseEntity.badRequest().body("Payment failed. Invalid payment amount or bill ID.");
	            }
	        } 
		  catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
	        }
	  }

}
