package com.smartparking.smart_parking_management_system.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.model.ParkingSpot;
import com.smartparking.smart_parking_management_system.repository.ParkingSpotRepository;

@Service 
public class ParkingSpotService  {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot){
        return parkingSpotRepository.save(parkingSpot);
    }

    public Optional<ParkingSpot> getParkingSpot(Long id){
        return parkingSpotRepository.findById(id);
    }

    public List<ParkingSpot> getAllParkingSpots(){
        return parkingSpotRepository.findAll();
    }

    public void deleteParkingSpot(Long id){
        parkingSpotRepository.deleteById(id);
    }
    




}