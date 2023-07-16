package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.StatusUpdate;
import com.example.entity.SupportTicket;
import com.example.repository.SupportTicketRepository;

@Service
public class SupportTicketService {
  private final SupportTicketRepository supportTicketRepository;
  private final ConsumerService consumerService;

  public SupportTicketService(SupportTicketRepository supportTicketRepository, ConsumerService consumerService) {
    this.supportTicketRepository = supportTicketRepository;
    this.consumerService = consumerService;
  }

  

  public SupportTicket createSupportTicket(SupportTicket supportTicket) {
      return supportTicketRepository.save(supportTicket);
  }

  public SupportTicket getSupportTicketById(Long id) {
      return supportTicketRepository.findById(id).orElse(null);
  }

  public List<SupportTicket> getAllSupportTickets() {
      return supportTicketRepository.findAll();
  }

  public void addStatusUpdate(Long supportTicketId, StatusUpdate statusUpdate) {
      SupportTicket supportTicket = supportTicketRepository.findById(supportTicketId).orElse(null);
      if (supportTicket != null) {
          supportTicket.addStatusUpdate(statusUpdate);
          supportTicketRepository.save(supportTicket);
      }
  }

}

