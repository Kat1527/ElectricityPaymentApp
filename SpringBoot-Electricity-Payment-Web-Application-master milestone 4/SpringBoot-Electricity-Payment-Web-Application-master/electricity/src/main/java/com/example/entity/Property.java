package com.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long propertyId;
  
  private String address;
  private String meterNumber;
  private String tariffPlan;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "consumer_id")
  @JsonIgnore
  Consumer consumer;

  @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Bill> bills;
  // Constructor, getters, and setters

@Override
public String toString() {
	return "Property [propertyId=" + propertyId + ", address=" + address + ", meterNumber=" + meterNumber
			+ ", tariffPlan=" + tariffPlan  + ", bills=" + bills + "]";
}
  
  
  
}
