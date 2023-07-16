package com.example.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BillGenerationRequestDTO;
import com.example.dto.PreviousBillDTO;
import com.example.entity.Bill;
import com.example.entity.Consumer;
import com.example.entity.Property;
import com.example.exception.NotFoundException;
import com.example.payloads.Payment;
import com.example.repository.BillRepository;
import com.example.repository.ConsumerRepository;
import com.example.repository.PropertyRepository;

@Service
public class BillService {
	
	@Autowired
	private Payment payment;
	
	@Autowired
	private ConsumerRepository consumerRepository;
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;




  public Bill generateMonthlyBill(BillGenerationRequestDTO requestDTO) {
    // Implement logic to generate monthly bills for consumers
    // Fetch consumer from database based on consumerId
    Consumer consumer = consumerRepository.findById(requestDTO.getConsumerId())
      .orElseThrow(() -> new NotFoundException("Consumer", "Consumer ID", requestDTO.getConsumerId()));
    
    // Find the property for which Bill has to be generated
    Property billProperty = new Property();
    
    for(Property p: consumer.getProperties())
    {
    	if(requestDTO.getPropertyId().equals(p.getPropertyId()))
    	{
    		billProperty = p;
    		break;
    	}

    }

    // Calculate bill amount based on units consumed and rate for the consumer's group
    
    String tariff = billProperty.getTariffPlan();
    double totalConsumption = requestDTO.getUnitsConsumed();
    double perUnitRate = payment.determinePerUnitRate(totalConsumption, tariff);
    
    double billAmount = payment.calculateBillAmount(perUnitRate, totalConsumption);

    // Create and save the bill entity
    Bill bill = new Bill();
    bill.setUnitsConsumed(totalConsumption);
    bill.setProperty(billProperty);
    bill.setAmount(billAmount);
    bill.setDueDate(getDueDate());
    bill.setBillingDate(requestDTO.getBillingDate());
    // Set other properties as needed
    return billRepository.save(bill);
  }
  
  public LocalDate getDueDate() {
      LocalDate currentDate = LocalDate.now();
      LocalDate newDueDate = currentDate.plusDays(15);
      
      return newDueDate;
  }

public List<Bill> generatePreviousBill(PreviousBillDTO previousBillDTO) {
	
	Property property = propertyRepository.findById(previousBillDTO.getPropertyId())
		      .orElseThrow(() -> new NotFoundException("Property", "Property ID", previousBillDTO.getPropertyId()));
	
	
	List<Bill> bills = new ArrayList<>();
	LocalDate from = previousBillDTO.getFrom();
	LocalDate to = previousBillDTO.getTo();
	
	for(Bill b: property.getBills())
	{
		Month month = b.getBillingDate().getMonth();
        
		if (month.compareTo(from.getMonth()) >= 0 && month.compareTo(to.getMonth()) <= 0) {
			bills.add(b);
        }
		
	}
	
	return bills;
}

public List<Bill> generatePendingBill(Long propertyId) {
	
	Property property = propertyRepository.findById(propertyId)
		      .orElseThrow(() -> new NotFoundException("Property", "Property ID", propertyId));
	
	List<Bill> bills = new ArrayList<>();
	
	for(Bill b: property.getBills())
	{
		if(!b.isPaid())
			bills.add(b);
	}
	
	return bills;
	 
}






  
}

