package com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
  Tenant findByName(String tenantId);
}
