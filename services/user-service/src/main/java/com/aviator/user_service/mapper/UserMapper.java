package com.aviator.user_service.mapper;


import com.aviator.payload.dto.UserDto;
import com.aviator.user_service.model.User;

public class UserMapper {
    
    public static UserDto UserEntityToUserDto(User user) {
        if (user == null) {
            return null;
        }
        
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .userRole(user.getRole())
                .lastLoginTimestamp(user.getLastLogin())
                .build();
    }

    public static User UserDtoToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .phone(userDto.getPhone())
                .role(userDto.getUserRole())
                .lastLogin(userDto.getLastLoginTimestamp())
                .build();
    }
}
