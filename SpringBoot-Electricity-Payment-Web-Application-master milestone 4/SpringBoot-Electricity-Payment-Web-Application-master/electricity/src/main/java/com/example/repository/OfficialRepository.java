package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Official;

@Repository
public interface OfficialRepository extends JpaRepository<Official, Long> {
  // Add any additional query methods if needed
}
