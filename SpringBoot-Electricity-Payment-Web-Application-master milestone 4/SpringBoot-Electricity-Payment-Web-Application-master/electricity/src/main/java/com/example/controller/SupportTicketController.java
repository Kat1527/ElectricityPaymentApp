package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.StatusUpdate;
import com.example.entity.SupportTicket;
import com.example.service.SupportTicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/support-tickets")
public class SupportTicketController {
	
	@Autowired
  private final SupportTicketService supportTicketService;

  public SupportTicketController(SupportTicketService supportTicketService) {
    this.supportTicketService = supportTicketService;
  }

  
  @PostMapping
  public SupportTicket createSupportTicket(@RequestBody @Valid SupportTicket supportTicket) {
      return supportTicketService.createSupportTicket(supportTicket);
  }

  @GetMapping("/{id}")
  public SupportTicket getSupportTicketById(@PathVariable("id") Long id) {
      return supportTicketService.getSupportTicketById(id);
  }

  @GetMapping
  public List<SupportTicket> getAllSupportTickets() {
      return supportTicketService.getAllSupportTickets();
  }

  @PostMapping("/{supportTicketId}/status-updates")
  public void addStatusUpdate(
          @PathVariable("supportTicketId") Long supportTicketId,
          @RequestBody @Valid StatusUpdate statusUpdate
  ) {
      supportTicketService.addStatusUpdate(supportTicketId, statusUpdate);
  }
  
}
