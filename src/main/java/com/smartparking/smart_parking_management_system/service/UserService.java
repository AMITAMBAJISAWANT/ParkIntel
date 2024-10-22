package com.smartparking.smart_parking_management_system.service;

import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.model.User1;
import com.smartparking.smart_parking_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public void registerUser(User1 newUser)throws Exception{
        userRepository.save(newUser);

        String Subject="Registration successful";
        String body="Dear"+newUser.getUsername()+",<br/> Your registration is successful!";

    }
    



    
}