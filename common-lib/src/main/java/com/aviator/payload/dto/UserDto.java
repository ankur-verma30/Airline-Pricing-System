package com.aviator.payload.dto;

import java.time.LocalDateTime;

import com.aviator.payload.enums.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private UserRoles userRole;
    private LocalDateTime lastLoginTimestamp;
}
