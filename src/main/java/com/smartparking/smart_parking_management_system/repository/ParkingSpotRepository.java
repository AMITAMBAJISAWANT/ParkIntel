package com.smartparking.smart_parking_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
    
}