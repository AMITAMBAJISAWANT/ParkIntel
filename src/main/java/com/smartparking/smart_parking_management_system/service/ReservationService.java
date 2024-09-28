package com.smartparking.smart_parking_management_system.service;

import java.time.LocalDateTime;
import java.util.List;

import com.smartparking.smart_parking_management_system.model.Reservation;

public interface ReservationService {
    Reservation createReservation(Long userId,Long parkingSpotId,LocalDateTime requestedStartTime, int durationInHours) throws Exception;
    List<Reservation> getReservationByUser(Long userId);
    List<Reservation> getAllReservations();

    
} 