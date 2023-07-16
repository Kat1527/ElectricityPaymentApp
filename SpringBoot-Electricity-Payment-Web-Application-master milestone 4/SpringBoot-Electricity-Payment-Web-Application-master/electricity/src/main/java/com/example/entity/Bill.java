package com.example.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
 



@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double amount;
  private LocalDate dueDate;
  private LocalDate billingDate;
  private boolean isPaid;
  private double unitsConsumed;


  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "property_id")
  @JsonIgnore
  private Property property;

  // Constructors, getters, and setters

  


  // Getters and Setters

  @Override
 	public String toString() {
 		return "Bill [id=" + id + ", amount=" + amount + ", dueDate=" + dueDate + ", billingDate=" + billingDate
 				+ ", isPaid=" + isPaid + ", unitsConsumed=" + unitsConsumed + "]";
 	}
  
  
}
