package com.example.multi_tenant_role_based_access_control_system.presentation;

import com.example.multi_tenant_role_based_access_control_system.application.dto.UserRequestDto;
import com.example.multi_tenant_role_based_access_control_system.application.dto.UserResponseDto;
import com.example.multi_tenant_role_based_access_control_system.application.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    UserResponseDto userResponseDto = userService.createUser(userRequestDto);
    return ResponseEntity.ok(userResponseDto);
  }

  @GetMapping("/username")
  public ResponseEntity<UserResponseDto> getUser(@RequestParam String username) {
    UserResponseDto userResponseDto = userService.getUser(username).orElse(null);
    return ResponseEntity.ok(userResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    List<UserResponseDto> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @DeleteMapping("/username")
  public ResponseEntity<Void> deleteUser(@RequestParam String username) {
    userService.deleteUser(username);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/username")
  public ResponseEntity<UserResponseDto> updateUser(@RequestParam String username, @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto userResponseDto = userService.updateUser(username, userRequestDto);
    return ResponseEntity.ok(userResponseDto);
  }

}
