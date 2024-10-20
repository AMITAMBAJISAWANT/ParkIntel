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
           String text = String.format(
                "<p>Dear %s,</p>"
                + "<p>Your parking spot at <strong>%s</strong> has been reserved from <strong>%s</strong> to <strong>%s</strong>.</p>"
                + "<p>Thank you for using our service!</p>",
                user.getUsername(),
                parkingSpot.getLocation(),
                startTime.toString(),
                endTime.toString()
                );
           emailService.sendEmail(user.getEmail(), subject, text);

    }
    public void sendExpirationReminder(User1 user,ParkingSpot parkingSpot,LocalDateTime endTime){
        String subject ="Parking Spot Reservation Expiring Soon!";
        String text = String.format(
                "<p>Dear %s,</p>"
                + "<p>Your reservation for parking spot at <strong>%s</strong> will expire at <strong>%s</strong>.</p>"
                + "<p>Please ensure you vacate the spot on time.</p>"
                + "<p>Thank you!</p>",
                user.getUsername(),
                parkingSpot.getLocation(),
                endTime.toString()
        );
        emailService.sendEmail(user.getEmail(), subject, text);
    }      
    
}