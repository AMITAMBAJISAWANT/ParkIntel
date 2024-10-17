package com.smartparking.smart_parking_management_system.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    
    private final JavaMailSender mailSender;

    public void sendEmail(String to,String subject,String text){
        MimeMessage message=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
            helper.setFrom("amitambajisawant1@gmail.com");
            mailSender.send(message);

        }
        catch(MessagingException e){
           throw new IllegalStateException("Fail to send email",e);
           


        }
    }


    
}