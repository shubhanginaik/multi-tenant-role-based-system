package com.example.multi_tenant_role_based_access_control_system.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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
public class UserRequestDto {

  @Size(max = 45, message = "Field must be at most 45 characters")
  @NotNull(message = "Field is required")
  @Column(unique = true)
  private String username;

  @Size(max = 45, message = "Field must be at most 45 characters")
  @NotNull(message = "Field is required")
  @Column(unique = true)
  private String email;

  @Size(min = 6, max = 45, message = "Field must be at most 45 characters")
  @NotNull(message = "Field is required")
  private String password;

  private LocalDateTime createdAt;
}
