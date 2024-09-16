package com.smartparking.smart_parking_management_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.exception.ResourceNotFoundException;
import com.smartparking.smart_parking_management_system.model.ParkingSpot;
import com.smartparking.smart_parking_management_system.model.Reservation;
import com.smartparking.smart_parking_management_system.model.User1;
import com.smartparking.smart_parking_management_system.repository.ParkingSpotRepository;
import com.smartparking.smart_parking_management_system.repository.ReservationRepository;
import com.smartparking.smart_parking_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService  {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public Reservation createReservation(Long userId,Long parkingSpotId){
        
        User1 user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        ParkingSpot parkingSpot=parkingSpotRepository.findById(parkingSpotId).orElseThrow(()->new ResourceNotFoundException("Parking spot not found"));

        Reservation reservation =new  Reservation();
        reservation.setUser(user);
        reservation.setParkingSpot(parkingSpot);
        reservation.setReservationTime(LocalDateTime.now());

        return reservationRepository.save(reservation);


    }
    
    @Override
    public List<Reservation> getReservationByUser(Long userId){
       User1 user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(("User not found")));
       return reservationRepository.findByUser(user);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    
}