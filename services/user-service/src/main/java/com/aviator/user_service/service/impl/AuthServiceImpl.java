package com.aviator.user_service.service.impl;

import org.springframework.stereotype.Service;

import com.aviator.payload.dto.UserDto;
import com.aviator.payload.enums.UserRoles;
import com.aviator.payload.response.AuthResponse;
import com.aviator.user_service.repository.UserRepository;
import com.aviator.user_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse signUp(UserDto userDto) throws Exception {
        /*
        1. Check if the user already exists
        
        2. Encode the password using bcrypt
        3. save user the database
        4. generate the jwt token
        5. Return the authResponse */

        userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("User already exists"));
        if(userDto.getUserRole().equals(UserRoles.ROLE_SYSTEM_ADMIN)){
            throw new Exception("System Admin cannot be created");
        }
    }

    @Override
    public AuthResponse login(String email, String password) {
        
    }



}
