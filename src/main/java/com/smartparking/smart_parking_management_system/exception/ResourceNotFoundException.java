package com.smartparking.smart_parking_management_system.exception;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String message){
        super(message);
    }
    
}