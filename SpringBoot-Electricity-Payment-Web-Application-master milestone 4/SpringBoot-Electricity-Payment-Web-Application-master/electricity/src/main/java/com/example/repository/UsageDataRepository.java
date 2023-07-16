package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.UsageData;

@Repository
public interface UsageDataRepository extends JpaRepository<UsageData, String> {
  // Custom queries if needed
}
