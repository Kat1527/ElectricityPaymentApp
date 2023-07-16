package com.example.service;

import org.springframework.stereotype.Service;

import com.example.repository.UsageDataRepository;

@Service
public class UsageDataService {
  private final UsageDataRepository usageDataRepository;

  public UsageDataService(UsageDataRepository usageDataRepository) {
    this.usageDataRepository = usageDataRepository;
  }

  // Implement methods for usage data-related operations
}
