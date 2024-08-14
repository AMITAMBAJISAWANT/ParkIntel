package com.smartparking.smart_parking_management_system.util;

import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

public class JwtUtil  {
    private String secret;
    public JwtUtil(){
        this.secret = generateSecretKey();
    }
    private String generateSecretKey(){
        try{
            KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA512");
            SecretKey secretKey=keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch(Exception e){
            throw new RuntimeException("Error generating secret key", e);

        }
    }
    public String getUsernameFromToken(String token){
        return token;
        
    }
    

    
    
}