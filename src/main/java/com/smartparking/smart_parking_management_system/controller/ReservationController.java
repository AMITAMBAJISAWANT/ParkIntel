package com.smartparking.smart_parking_management_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.smart_parking_management_system.model.Reservation;
import com.smartparking.smart_parking_management_system.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController  {

    private final ReservationService reservationService;

    



    
}