package com.smartparking.smart_parking_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
    
}