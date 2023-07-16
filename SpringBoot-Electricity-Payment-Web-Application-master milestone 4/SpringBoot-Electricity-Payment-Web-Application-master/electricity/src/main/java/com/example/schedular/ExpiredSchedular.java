package com.example.schedular;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dto.BillGenerationRequestDTO;
import com.example.entity.Bill;
import com.example.entity.Consumer;
import com.example.entity.Property;
import com.example.repository.PropertyRepository;
import com.example.service.BillService;
import com.example.service.ConsumerService;
import com.example.service.DepartmentOfficialService;

@Component
public class ExpiredSchedular {
	
    private final Logger logger = LoggerFactory.getLogger(ExpiredSchedular.class);

	
	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	DepartmentOfficialService officialService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Scheduled(initialDelay=50000, fixedDelay = 50000)
	public void markExpired()
	{
		
		System.out.println("Inside Schedular...");
		
		List<Consumer> consumers = officialService.allConsumers();
		
		try {
//			for(Consumer c: consumers)
//			{
//				 System.out.println(c);
				//List<Property> properties = consumerService.getPropertiesByConsumerId(c.getId());
				for(Property p: propertyRepository.findAll())
				{
					//System.out.println(p);
					double min = 100;
			        double max = 2000;

			        Random random = new Random();
			        double randomNumber = random.nextDouble(max - min + 1) + min;
					BillGenerationRequestDTO request = new BillGenerationRequestDTO(p.getConsumer().getId(), p.getPropertyId(), randomNumber,LocalDate.now());
					
					Bill bill = billService.generateMonthlyBill(request);
					System.out.println("===============================");
					System.out.println("==============BILL=================");
					
					logger.info("\nCalculated bill for Consumer " + p.getConsumer().getId() +
	                        ", \nProperty " + p.getPropertyId() +
	                        "\nTariff Plan: " + p.getTariffPlan() +
	                        "\nUnits Consumed: " +bill.getUnitsConsumed() 
	                        +": \nRs" + bill.getAmount() + "\nBilling Date: " + bill.getBillingDate()
	                        + "\nDue Date: " + bill.getDueDate() );
					
					if(p.getTariffPlan().equals("HOUSEHOLD") && bill.getUnitsConsumed()>1000)
						System.out.println("WARNING: UNIT LIMIT EXCEEDED");
					 
					if(p.getTariffPlan().equals("INDUSTRIAL") && bill.getUnitsConsumed()>1200)
						System.out.println("WARNING: UNIT LIMIT EXCEEDED");
					
					if(p.getTariffPlan().equals("PUBLIC") && bill.getUnitsConsumed()>1300)
						System.out.println("WARNING: UNIT LIMIT EXCEEDED");
					
					System.out.println("===============================");
	    
				}
			
		}
				catch(Exception e)
		{
					System.out.println(e.getMessage());
		}
		
		
	}	
}