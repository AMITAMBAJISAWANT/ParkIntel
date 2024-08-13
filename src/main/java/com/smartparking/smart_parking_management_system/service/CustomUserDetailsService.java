package com.smartparking.smart_parking_management_system.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smartparking.smart_parking_management_system.model.User;
import com.smartparking.smart_parking_management_system.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService  {
      
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("User Not Found:"+username));
        return user.map(CustomUserDetails::new).get();
 

        
    }
    
}