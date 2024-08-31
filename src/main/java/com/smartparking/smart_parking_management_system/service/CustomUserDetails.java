package com.smartparking.smart_parking_management_system.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.smartparking.smart_parking_management_system.model.User1;

@Service
public class CustomUserDetails implements UserDetails {

   private User1 user;
    
    public CustomUserDetails(User1 user){
        this.user=user;
    }
    public CustomUserDetails(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
               .map(role->new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toList());
        
    }

    @Override
    public String getPassword() {
       return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    
    }

    @Override
    public boolean isEnabled() {
       return true;    
    }

}