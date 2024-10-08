package com.smartparking.smart_parking_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @ManyToOne
    @JoinColumn(name="parking_spot_id")
    private ParkingSpot parkingSpot;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User1 user;

    private LocalDateTime startTime;
    
    @Column(nullable = false)
    private LocalDateTime endTime;
   

    
}