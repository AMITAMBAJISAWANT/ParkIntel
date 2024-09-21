package com.smartparking.smart_parking_management_system.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Reservation> createReservation(@RequestParam Long userId,@RequestParam Long parkingSpotId ){
        Reservation reservation =reservationService.createReservation(userId, parkingSpotId);
        return new ResponseEntity<>(reservation,HttpStatus.OK);
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