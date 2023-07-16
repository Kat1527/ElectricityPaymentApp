package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

//	List<Property> findByConsumer(Consumer consumer);
  // Custom queries if needed
//	
//	List<Property> findAllByConsumer_Id(Long consumerId);
	
	@Query(value = "SELECT * FROM Property WHERE consumer_id = ?1", nativeQuery = true)
   List<Property> findPropertiesByConsumerId(Long consumerId);

	 
}
