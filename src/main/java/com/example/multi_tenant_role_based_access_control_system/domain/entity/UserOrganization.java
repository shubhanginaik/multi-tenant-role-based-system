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
@Table(name = "user_organization")
public class UserOrganization {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne  // Many-to-One with User
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne  // Many-to-One with Organization
  @JoinColumn(name = "org_id", nullable = false)
  private Organization organization;

  @ManyToOne  // Many-to-One with Role
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;
}

