package com.example.multi_tenant_role_based_access_control_system.application.service;

import com.example.multi_tenant_role_based_access_control_system.application.dto.UserRequestDto;
import com.example.multi_tenant_role_based_access_control_system.application.dto.UserResponseDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
  UserResponseDto createUser(UserRequestDto requestUserDto);
  Optional<UserResponseDto> getUser(String username);
  List<UserResponseDto> getAllUsers();
  void deleteUser(String username);
  UserResponseDto updateUser(String username, UserRequestDto requestUserDto);

}
