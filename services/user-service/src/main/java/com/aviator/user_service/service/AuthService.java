package com.aviator.user_service.service;

import com.aviator.payload.dto.UserDto;
import com.aviator.payload.response.AuthResponse;

public interface AuthService {
    
    AuthResponse signUp(UserDto userDto) throws Exception;
    AuthResponse login(String email, String password);
}
