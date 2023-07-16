package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BillDTO;
import com.example.dto.PropertyDTO;
import com.example.entity.Bill;
import com.example.entity.Consumer;
import com.example.entity.Property;
import com.example.exception.NotFoundException;
import com.example.repository.BillRepository;
import com.example.repository.ConsumerRepository;
import com.example.repository.PropertyRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;

  


  public List<List<Bill>> getBillsByConsumerId(Long consumerId) {
    // Implement logic to fetch bills for a specific consumer
    // Fetch consumer from database based on consumerId
    Consumer consumer = consumerRepository.findById(consumerId)
      .orElseThrow(() -> new NotFoundException("Consumer", "Consumer ID", consumerId));

    // Fetch bills associated with the consumer
    //List<Bill> bills = billRepository.findByConsumer(consumer);

    // Convert bills to BillDTOs and return the list
//    return bills.stream()
//      .map(this::convertToBillDTO)
//      .collect(Collectors.toList());
    
    List<Property> properties = consumer.getProperties();
    List<List<Bill>> bills = new ArrayList<>();
    
    for(Property p: properties)
    {
    	bills.add(p.getBills());
    }
    
    return bills;
  }

  private BillDTO convertToBillDTO(Bill bill) {
    // Convert Bill entity to BillDTO
    BillDTO billDTO = new BillDTO();
    billDTO.setId(bill.getId());
    billDTO.setAmount(bill.getAmount());
    // Set other properties as needed
    return billDTO;
  }

  @Transactional
public List<Property> getPropertiesByConsumerId(Long consumerId) {
	// TODO Auto-generated method stub
	
	//Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(()-> new NotFoundException("Consumer not found"));
	
	//List<Property> properties = consumer.getProperties();	
	
	//List<Property> properties = propertyRepository.findByConsumer(consumer);
//	
	List<Property> properties = new ArrayList<>();

		properties =  propertyRepository.findPropertiesByConsumerId(consumerId);
		
		 
	
	
	if(properties.size()==0 || properties==null)
	{
		throw new NotFoundException("Consumer", "Consumer ID", consumerId);
	}
	
	return properties;
 
    
}
  
  

public void addUserProperty(Long consumerId, PropertyDTO propertyDTO) {
	
	Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(()-> new NotFoundException("Consumer", "Consumer ID", consumerId));
	Property property = new Property();
	
	property.setConsumer(consumer);
	property.setAddress(propertyDTO.getAddress());
	property.setMeterNumber(propertyDTO.getMeterNumber());
	property.setTariffPlan(propertyDTO.getTariffPlan());
	
	consumer.getProperties().add(property);
	
	consumerRepository.save(consumer);
	
}
}
