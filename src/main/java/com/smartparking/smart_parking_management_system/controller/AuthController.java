package com.smartparking.smart_parking_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartparking.smart_parking_management_system.dto.UserRegistrationDTO;
import com.smartparking.smart_parking_management_system.model.Role;
import com.smartparking.smart_parking_management_system.model.User1;
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

    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationDTO userRegistrationDTO){
    User1 user =new User1();
    user.setUsername(userRegistrationDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
    user.setEmail(userRegistrationDTO.getEmail());
                                                                                       
    Role role = roleRepository.findByName("User"); 
    // if (role == null) {
    //     // If the role does not exist, create and save it
    //     role = new Role();
    //     role.setName("User");
    //     roleRepository.save(role);
    // }
    user.getRoles().add(role);
    userRepository.save(user);

    return "User registered successfully";

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRegistrationDTO userRegistrationDTO){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword()));
           SecurityContextHolder.getContext().setAuthentication(authentication);
           UserDetails userDetails= (UserDetails) authentication.getPrincipal();
           String jwt=jwtUtil.generateToken(userDetails);
           return ResponseEntity.ok(jwt);
        
    }
    
    
    
}