package com.example.multi_tenant_role_based_access_control_system.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class RolePermissions {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne  // Many-to-One with Permission
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @ManyToOne  // Many-to-One with Permission
  @JoinColumn(name = "permission_id", nullable = false)
  private Permission permission;



}