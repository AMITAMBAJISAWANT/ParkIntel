package com.smartparking.smart_parking_management_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController  {
    
    
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    public ResponseEntity<String> getUserDashboard(){
        return ResponseEntity.ok("User Dashboard Content");

    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> getAdminDashboard(){
        return ResponseEntity.ok("Admin_only Dashboard Content");

    }

    
}