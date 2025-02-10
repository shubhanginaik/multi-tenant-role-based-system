package com.example.multi_tenant_role_based_access_control_system.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "audit_logs")
public class AuditLogs {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String action;
  private LocalDateTime createdAt;

  @ManyToOne  // Many-to-One with User
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
