package com.smartparking.smart_parking_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smartparking.smart_parking_management_system.model.Role;
import com.smartparking.smart_parking_management_system.repository.RoleRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.findByName("User") == null) {
            Role userRole = new Role();
            userRole.setName("User");
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName("Admin") == null) {
            Role adminRole = new Role();
            adminRole.setName("Admin");
            roleRepository.save(adminRole);
        }

    }

}