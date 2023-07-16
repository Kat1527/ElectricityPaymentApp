package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Consumer;
import com.example.entity.Property;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

	 
	
	
  // Add any additional query methods if needed
//	
//	@Query(value = "SELECT * FROM Consumer WHERE consumer_id = ?1", nativeQuery = true)
//    List<Property> findPropertiesByConsumerId(Long consumerId);
	
//	@Query(value = "SELECT p.* FROM property p WHERE p.consumer_id = :consumerId", nativeQuery = true)
//	List<Property> findPropertiesByConsumerId(@Param("consumerId") Long consumerId);
//	
//	@Query(value = "SELECT * FROM properties WHERE consumer_id = :consumerId", nativeQuery = true)
//	List<Object[]> findPropertiesByConsumerId(@Param("consumerId") Long consumerId);


}
