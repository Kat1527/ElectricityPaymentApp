package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ConsumerDTO;
import com.example.entity.Consumer;
import com.example.exception.ConsumerNotFoundException;
import com.example.repository.ConsumerRepository;

@Service
public class DepartmentOfficialService {
	
	@Autowired
  private final ConsumerRepository consumerRepository;

  public DepartmentOfficialService(ConsumerRepository consumerRepository) {
    this.consumerRepository = consumerRepository;
  }

  public void registerConsumer(ConsumerDTO consumerDTO) {
    // Implement logic to register new consumers
    // Create Consumer entity from DTO and save it to the database
    Consumer consumer = new Consumer();
    consumer.setName(consumerDTO.getName());
    consumer.setEmail(consumerDTO.getEmail());
    consumer.setPassword(consumerDTO.getPassword());
     
    // Set other properties as needed
    consumerRepository.save(consumer);
  }
  
  
  public List<Consumer> allConsumers() throws ConsumerNotFoundException
  {
	  List<Consumer> consumers = consumerRepository.findAll();
	  
	  if(consumers==null || consumers.size()==0)
		  throw new  ConsumerNotFoundException("Consumers", "Consumers", "CONSUMERS");
	  
	  return consumers;
  }
}
