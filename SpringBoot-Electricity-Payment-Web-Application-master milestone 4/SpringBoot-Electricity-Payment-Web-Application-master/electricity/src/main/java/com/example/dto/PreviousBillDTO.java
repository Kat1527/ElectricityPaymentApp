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
public class PreviousBillDTO {
	
	 private Long propertyId;
	 private LocalDate from;
	 private LocalDate to;
	 

}
