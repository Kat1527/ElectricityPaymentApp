package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.OfficialDTO;
import com.example.entity.Official;
import com.example.repository.OfficialRepository;

@Service
public class AdminService {
	
	@Autowired
  private final OfficialRepository officialRepository;

  public AdminService(OfficialRepository officialRepository) {
    this.officialRepository = officialRepository;
  }

  public void registerOfficial(OfficialDTO officialDTO) {
    // Implement logic to register new officials
    // Create Official entity from DTO and save it to the database
    Official official = new Official();
    official.setName(officialDTO.getName());
    official.setEmail(officialDTO.getEmail());
    official.setPassword(officialDTO.getPassword());
    // Set other properties as needed
    officialRepository.save(official);
  }
}
