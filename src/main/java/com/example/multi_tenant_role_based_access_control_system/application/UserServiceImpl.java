package com.example.multi_tenant_role_based_access_control_system.application;

import com.example.multi_tenant_role_based_access_control_system.application.dto.UserRequestDto;
import com.example.multi_tenant_role_based_access_control_system.application.dto.UserResponseDto;
import com.example.multi_tenant_role_based_access_control_system.application.mapper.UserMapper;
import com.example.multi_tenant_role_based_access_control_system.application.service.UserService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.User;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserResponseDto createUser(UserRequestDto requestUserDto) {
    User user = UserMapper.toUser(requestUserDto);
    user = userRepository.save(user);
    return UserMapper.toUserDto(user);
  }

  @Override
  public Optional<UserResponseDto> getUser(String username) {
    User user = userRepository.findByUsername(username);
    return Optional.ofNullable(UserMapper.toUserDto(user));
  }

  @Override
  public List<UserResponseDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(UserMapper::toUserDto)
        .toList();
  }

  @Override
  public void deleteUser(String username) {
    User user = userRepository.findByUsername(username);
    userRepository.delete(user);
  }

  @Override
  public UserResponseDto updateUser(String username, UserRequestDto requestUserDto) {
    User user = userRepository.findByUsername(username);
    if(user == null) {
      return null;
    }
    if(requestUserDto.getUsername() != null) {
      user.setUsername(requestUserDto.getUsername());
    }
    if(requestUserDto.getEmail() != null) {
      user.setEmail(requestUserDto.getEmail());
    }
    if(requestUserDto.getPassword() != null) {
    user.setPassword(requestUserDto.getPassword());
    }
    user = userRepository.save(user);
    return UserMapper.toUserDto(user);
  }
}
