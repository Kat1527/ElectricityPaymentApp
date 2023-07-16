package com.example.service;

import org.springframework.stereotype.Service;

import com.example.repository.PropertyRepository;

@Service
public class PropertyService {
  private final PropertyRepository propertyRepository;

  public PropertyService(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  // Implement methods for property-related operations
}
