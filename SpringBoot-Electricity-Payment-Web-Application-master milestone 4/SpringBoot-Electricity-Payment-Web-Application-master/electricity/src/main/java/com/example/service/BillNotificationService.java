package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.BillRepository;

@Service
public class BillNotificationService {
	@Autowired
  private final BillRepository billRepository;
  
	@Autowired
	private final NotificationService notificationService;

  public BillNotificationService(BillRepository billRepository, NotificationService notificationService) {
    this.billRepository = billRepository;
    this.notificationService = notificationService;
  }

  // Implement methods for bill reminders and notifications
}
