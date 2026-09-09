package com.aviator.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aviator.payload.response.AuthResponse;

import com.aviator.user_service.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsContructor
public class AuthController{

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(authService.signup(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authService.signin(userDto));
    }

}
