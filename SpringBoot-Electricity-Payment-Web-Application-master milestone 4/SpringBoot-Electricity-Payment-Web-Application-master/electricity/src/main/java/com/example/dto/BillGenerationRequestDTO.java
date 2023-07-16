package com.example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillGenerationRequestDTO {

  private Long consumerId;
  private Long propertyId;
  private double unitsConsumed;
  private LocalDate billingDate;

  // Constructors, getters, and setters

  }
