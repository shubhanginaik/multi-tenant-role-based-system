package com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
  Optional<Permission> findByName(String permissionName);

}
