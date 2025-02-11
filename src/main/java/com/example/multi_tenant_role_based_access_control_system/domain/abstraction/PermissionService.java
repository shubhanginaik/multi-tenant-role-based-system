package com.example.multi_tenant_role_based_access_control_system.domain.abstraction;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
  Permission createPermission(String permissionName);

  Optional<Permission> getPermissionByName(String permissionName);

  Optional<Permission> getPermissionById(UUID id);

  void deletePermission(UUID id);

  List<Permission> getAllPermissions();

}
