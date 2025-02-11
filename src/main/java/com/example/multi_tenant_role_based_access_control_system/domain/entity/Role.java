package com.example.multi_tenant_role_based_access_control_system.domain.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String name;  // e.g., "ADMIN", "USER"

  @Column
  private String description;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<RolePermissions> rolePermissions = new HashSet<>();

  // Get permissions directly from RolePermissions mapping
  public Set<Permission> getPermissions() {
    return rolePermissions.stream()
        .map(RolePermissions::getPermission)
        .collect(Collectors.toSet());
  }
}
