package com.example.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Bill;
import com.example.entity.Consumer;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
	
	@Query(value = "SELECT * FROM Bill WHERE property_id = ?1", nativeQuery = true)
	List<Bill> findBillsByPropertId(long propertyId);
    

    // Custom queries if needed
}
