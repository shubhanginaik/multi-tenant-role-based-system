package com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {
  Role findByName(String roleName);

}
