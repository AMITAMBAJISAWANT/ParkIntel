package com.smartparking.smart_parking_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class SmartParkingManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingManagementSystemApplication.class, args);
		System.out.println("Hello world");
		
		
	}

}
