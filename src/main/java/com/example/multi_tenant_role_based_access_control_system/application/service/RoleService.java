package com.example.multi_tenant_role_based_access_control_system.application.service;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import java.util.List;
import java.util.UUID;

public interface RoleService {

  Role createRole(String roleName);

  List<Role> getAllRoles();

  Role getRoleByName(String roleName);

  Role getRoleById(UUID id);

  void deleteRole(UUID id);
}
