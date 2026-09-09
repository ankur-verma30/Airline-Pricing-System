package com.aviator.payload.response;

import com.aviator.payload.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String jwt;
    private String message;
    private String title;
    private UserDto userDto;
}
