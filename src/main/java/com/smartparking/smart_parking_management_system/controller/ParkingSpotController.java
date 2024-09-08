package com.smartparking.smart_parking_management_system.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;
import com.smartparking.smart_parking_management_system.service.ParkingSpotService;

@RestController
@RequestMapping("/parkingspots")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;
    
    @GetMapping
    public List<ParkingSpot> getAllParkingSpots(){
        return parkingSpotService.getAllParkingSpots();
    }
     
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> getParkingSpotById(@PathVariable Long id){
        Optional<ParkingSpot> parkingSpot=parkingSpotService.getParkingSpotById(id);
        return parkingSpot.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
        
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ParkingSpot createParkingSpot(@RequestBody ParkingSpot parkingSpot){
        return parkingSpotService.saveParkingSpot(parkingSpot);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable Long id,@RequestBody ParkingSpot parkingSpotDetails){
    Optional<ParkingSpot> parkingSpot = parkingSpotService.getParkingSpotById(id);
     if(parkingSpot.isPresent()){
        ParkingSpot existiParkingSpot=parkingSpot.get();
        existiParkingSpot.setLocation(parkingSpotDetails.getLocation());
        existiParkingSpot.setAvailable(parkingSpotDetails.isAvailable());
        ParkingSpot updateParkingSpot=parkingSpotService.saveParkingSpot(existiParkingSpot);
        return ResponseEntity.ok(updateParkingSpot);
     }else{
        return ResponseEntity.notFound().build();
     }
    }
      @DeleteMapping("/{id}")
      @PreAuthorize("hasAuthority('Admin')")
     public ResponseEntity<Void> deleteParkingSpot(@PathVariable Long id){
        Optional<ParkingSpot> parkingSpot = parkingSpotService.getParkingSpotById(id);
        if(parkingSpot.isPresent()){
            parkingSpotService.deleteParkingSpot(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }
}