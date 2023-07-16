package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.UsageDataService;

@RestController
@RequestMapping("/usage-data")
public class UsageDataController {
	@Autowired
  private final UsageDataService usageDataService;

  public UsageDataController(UsageDataService usageDataService) {
    this.usageDataService = usageDataService;
  }

  // Implement usage data-related endpoints
}
