package com.example.multi_tenant_role_based_access_control_system.application.mapper;


import com.example.multi_tenant_role_based_access_control_system.application.dto.UserRequestDto;
import com.example.multi_tenant_role_based_access_control_system.application.dto.UserResponseDto;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserResponseDto toUserDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(String.valueOf(user.getCreatedAt()))
                .build();
    }

    public static User toUser(UserRequestDto requestUserDto) {
        return User.builder()
                .username(requestUserDto.getUsername())
                .email(requestUserDto.getEmail())
                .password(requestUserDto.getPassword())
                .build();
    }

}
