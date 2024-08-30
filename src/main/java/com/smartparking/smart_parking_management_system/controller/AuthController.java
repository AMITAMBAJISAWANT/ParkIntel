package com.smartparking.smart_parking_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.smart_parking_management_system.model.Role;
import com.smartparking.smart_parking_management_system.repository.RoleRepository;
import com.smartparking.smart_parking_management_system.repository.UserRepository;
import com.smartparking.smart_parking_management_system.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    
    
}