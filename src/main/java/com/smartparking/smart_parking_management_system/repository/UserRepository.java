package com.smartparking.smart_parking_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartparking.smart_parking_management_system.model.User1;


public interface UserRepository extends JpaRepository<User1, Long> {
    
    Optional<User1> findByUsername(String username);

}
