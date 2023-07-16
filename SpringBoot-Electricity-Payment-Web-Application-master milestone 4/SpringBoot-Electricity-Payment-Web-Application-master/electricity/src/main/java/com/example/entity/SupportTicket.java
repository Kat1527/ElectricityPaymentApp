package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "support_ticket")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "supportTicket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusUpdate> statusUpdates = new ArrayList<>();

    // Constructors, getters, and setters

    public void addStatusUpdate(StatusUpdate statusUpdate) {
        statusUpdates.add(statusUpdate);
        statusUpdate.setSupportTicket(this);
    }

    public void removeStatusUpdate(StatusUpdate statusUpdate) {
        statusUpdates.remove(statusUpdate);
        statusUpdate.setSupportTicket(null);
    }

    // Other methods
}