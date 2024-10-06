package com.smartparking.smart_parking_management_system.service;

import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;
import com.smartparking.smart_parking_management_system.model.User1;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService  {
 
    private final EmailService emailService;

    public void sendReservationConfirmation(User1 user,ParkingSpot parkingSpot,LocalDateTime startTime,LocalDateTime endTime){
           String subject="Parking Spot Reserved!";
           String text=String.format("Dear %s,\n\nYour parking spot at %s has been reserved from %s to %s.\n\n Thank you for using our service",
           user.getUsername(),
           parkingSpot.getLocation(),
           startTime.toString(),
           endTime.toString());
           emailService.sendEmail(user.getEmail(), subject, text);

    }
    public void sendExpirationReminder(User1 user,ParkingSpot parkingSpot,LocalDateTime endTime){
        String subject ="Parking Spot Reservation Expiring Soon!";
        String text=String.format("Dear %s,\n\n Your reservation for parking spot at %s will expire at %s.\n\n Please ensure you vacate the spot on time.\n\n Thank you!",user.getUsername(),parkingSpot.getLocation(),endTime.toString());
        emailService.sendEmail(user.getEmail(), subject, text);
    }      
    
}