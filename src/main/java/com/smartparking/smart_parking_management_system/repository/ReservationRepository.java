package com.smartparking.smart_parking_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;
import com.smartparking.smart_parking_management_system.model.Reservation;
import com.smartparking.smart_parking_management_system.model.User1;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>  {
    List<Reservation> findByUser(User1 user);
    List<Reservation> findByParkingSpot(ParkingSpot parkingSpot );

    
}