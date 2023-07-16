package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class UsageData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long usageId;
 
  private Long propertyId;
  private Date date;
  private float unitUsage;
  // Constructor, getters, and setters
}
