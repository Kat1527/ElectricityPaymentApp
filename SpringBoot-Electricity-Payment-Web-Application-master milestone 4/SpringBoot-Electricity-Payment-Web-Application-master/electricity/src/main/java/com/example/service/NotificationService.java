package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  public void sendNotification(String recipient, String message) {
    // Logic for sending notifications (e.g., email, SMS, push notifications)
    // Implement the specific notification sending mechanism here
    System.out.println("Sending notification to: " + recipient);
    System.out.println("Notification message: " + message);
  }
}
