package com.aviator.user_service.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aviator.payload.dto.UserDto;
import com.aviator.payload.enums.UserRoles;
import com.aviator.user_service.mapper.UserMapper;
import com.aviator.payload.response.AuthResponse;
import com.aviator.user_service.model.User;
import com.aviator.user_service.repository.UserRepository;
import com.aviator.user_service.service.AuthService;
import com.aviator.user_service.config.JwtProvider;
import com.aviator.user_service.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signUp(UserDto userDto) throws Exception {

        // 1. Check if the user already exists
        userRepository.findByEmail(userDto.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("User already exists");
        });

        if (userDto.getUserRole().equals(UserRoles.ROLE_SYSTEM_ADMIN)) {
            throw new Exception("System Admin cannot be created");
        }

        // 2. Encode the password using bcrypt
        User newUser = User.builder().email(userDto.getEmail()).password(passwordEncoder.encode(userDto.getPassword()))
                .fullName(userDto.getFullName()).phone(userDto.getPhone()).role(userDto.getUserRole())
                .lastLogin(LocalDateTime.now()).build();

        // 3. save user the database
        User savedUser=userRepository.save(newUser);

        // 4. generate the jwt token
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        
        // 5. Return the authResponse */
        String jwt= jwtProvider.generateToken(authentication, savedUser.getId());
        
        return AuthResponse.builder()
        .jwt(jwt)
        .title("Welcome "+savedUser.getFullName())
        .message("User created successfully")
        .userDto(UserMapper.UserEntityToUserDto(savedUser))
        .build();

    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {
        // 1. Authenticate the user
        Authentication authentication = authenticateUser(email, password);

        // 2. Generate the JWT token
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String jwt = jwtProvider.generateToken(authentication, user.getId());

        // 3. Update last login timestamp
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // 4. Return the authResponse
        return AuthResponse.builder()
                .jwt(jwt)
                .title("Welcome back " + user.getFullName())
                .message("Login successful")
                .userDto(UserMapper.UserEntityToUserDto(user))
                .build();
    }

    private Authentication authenticateUser(String email, String password){
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
