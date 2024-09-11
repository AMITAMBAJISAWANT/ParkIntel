package com.smartparking.smart_parking_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Reservation  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
}