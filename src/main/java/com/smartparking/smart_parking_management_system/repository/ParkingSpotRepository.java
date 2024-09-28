package com.smartparking.smart_parking_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;

import jakarta.persistence.LockModeType;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ParkingSpot p WHERE p.id = :id")
    ParkingSpot findByIdForUpdate(Long id);
}