package com.example.multi_tenant_role_based_access_control_system.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tenants")
public class Tenant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;  // e.g., "tenant1", "tenant2"

  @Column(unique = true, nullable = false)
  private String schemaName;  // Schema for the tenant

}

