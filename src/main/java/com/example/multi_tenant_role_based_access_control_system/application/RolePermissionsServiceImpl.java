package com.example.multi_tenant_role_based_access_control_system.application;



import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.RolePermissionsService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.RolePermissions;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.RolePermissionsRepository;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionsServiceImpl {
  private final RolePermissionsRepository rolePermissionsRepository;

  public RolePermissionsServiceImpl(RolePermissionsRepository rolePermissionsRepository) {
    this.rolePermissionsRepository = rolePermissionsRepository;
  }

  @Transactional
  public void assignPermissionsToRole(Role role, Set<Permission> permissions) {
    for (Permission permission : permissions) {
      RolePermissions rolePermission = new RolePermissions();
      rolePermission.setRole(role);
      rolePermission.setPermission(permission);
      rolePermissionsRepository.save(rolePermission);
    }
  }
}

