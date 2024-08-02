package com.smartparking.smart_parking_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartparking.smart_parking_management_system.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);

}