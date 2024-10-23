package com.smartparking.smart_parking_management_system.service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.model.User1;
import com.smartparking.smart_parking_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public void registerUser(User1 newUser) throws Exception{
        userRepository.save(newUser);

        // Get current date and time
        String registrationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // Customize the email subject and body
        String Subject = "Welcome to Smart Parking! Your Registration is Successful";
        String body = 
            "Dear " + newUser.getUsername() + ",<br/><br/>" +
            "We are excited to welcome you to Smart Parking!<br/><br/>" +
            "Your registration was successful on <b>" + registrationDate + "</b>.<br/><br/>" +
            "Here are your account details:<br/>" +
            "<ul>" +
            "<li><b>Username:</b> " + newUser.getUsername() + "</li>" +
            "<li><b>Email:</b> " + newUser.getEmail() + "</li>" +
            "</ul>" +
            "You can now start reserving your parking spots easily and efficiently!<br/><br/>" +
            "If you have any questions, feel free to reach out to our support team at amitambajisawant1@gmail.com.<br/><br/>" +
            "Thank you for choosing us, and we look forward to serving you!<br/><br/>" +
            "Best regards,<br/>" +
            "The Smart Parking Team";

        
        emailService.sendEmail(newUser.getEmail(), Subject, body);
    }
    
}