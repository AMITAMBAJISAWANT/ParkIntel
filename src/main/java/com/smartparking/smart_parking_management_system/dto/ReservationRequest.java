package com.smartparking.smart_parking_management_system.dto;

import lombok.Data;

@Data
public class ReservationRequest  {
    // private Long userId;
    private Long parkingSpotId;
    private String startTime;
    private double durationInHours;
    
}