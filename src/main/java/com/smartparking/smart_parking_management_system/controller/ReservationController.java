package com.smartparking.smart_parking_management_system.controller;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.smart_parking_management_system.dto.ReservationRequest;
import com.smartparking.smart_parking_management_system.model.Reservation;
import com.smartparking.smart_parking_management_system.service.ReservationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController  {

    private final ReservationService reservationService;
     
    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequest ){
        try{
            LocalDateTime requestedStartTime = LocalDateTime.parse(reservationRequest.getStartTime());
            Reservation reservation =reservationService.createReservation(
            reservationRequest.getUserId(),
            reservationRequest.getParkingSpotId(),
            requestedStartTime,
            reservationRequest.getDurationInHours() );
            return new ResponseEntity<>(reservation,HttpStatus.CREATED);

        }
        catch(Exception e){
            return new ResponseEntity<>("Error creating reservation" + e.getMessage(),HttpStatus.BAD_REQUEST);
            
        }
        
    } 
    
    @GetMapping("/user/{userId}")
    //@PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<Reservation>> getReservationByUser(@PathVariable Long userId){
        List<Reservation> reservation=reservationService.getReservationByUser(userId);
        return new ResponseEntity<>(reservation,HttpStatus.OK);
        
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Reservation>> getAllReservation(){
        List<Reservation> reservations =reservationService.getAllReservations();
        return new ResponseEntity<>(reservations,HttpStatus.OK);
    }
    
}