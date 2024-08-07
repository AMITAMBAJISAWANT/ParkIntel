package com.smartparking.smart_parking_management_system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String username;
    private String password;
    private Set<String> roles;
}
