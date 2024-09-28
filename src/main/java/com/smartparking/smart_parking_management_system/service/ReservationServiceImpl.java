package com.smartparking.smart_parking_management_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Override
    @Transactional
    public Reservation createReservation(Long userId,Long parkingSpotId,LocalDateTime requestedStartTime, int durationInHours) throws Exception{
        
        User1 user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        ParkingSpot parkingSpot=parkingSpotRepository.findById(parkingSpotId).orElseThrow(()->new ResourceNotFoundException("Parking spot not found"));
         
        if(!parkingSpot.isAvailable()){
            throw new Exception("This parking spot is not currently available");
        }

        if(requestedStartTime.isBefore(LocalDateTime.now())){
            throw new Exception("You cannot book parking spot in the past");

        }

        LocalDateTime endTime = requestedStartTime.plusHours(durationInHours);

        List<Reservation> overlappingReservations = reservationRepository.findByParkingSpotAndEndTimeAfter(parkingSpot,LocalDateTime.now());
        for(Reservation reservation : overlappingReservations){
            if(reservation.getStartTime().isBefore(endTime) && reservation.getEndTime().isAfter(requestedStartTime)){
                throw new Exception("This parking spot is already reserved during the requested time");

            }
        }
        
        Reservation reservation =new  Reservation();
        reservation.setUser(user);
        reservation.setParkingSpot(parkingSpot);
        reservation.setStartTime(requestedStartTime);
        reservation.setEndTime(endTime);

        Reservation savedReservation = reservationRepository.save(reservation);
         
      

        return savedReservation;

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