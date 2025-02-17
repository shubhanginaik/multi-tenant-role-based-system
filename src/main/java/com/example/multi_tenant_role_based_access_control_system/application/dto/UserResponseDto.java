package com.example.multi_tenant_role_based_access_control_system.application.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserResponseDto {
  private UUID id;
  private String username;
  private String email;
  private String createdAt;
}
