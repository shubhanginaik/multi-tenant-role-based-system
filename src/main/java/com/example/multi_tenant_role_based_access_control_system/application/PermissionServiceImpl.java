package com.example.multi_tenant_role_based_access_control_system.application;

import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.PermissionService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.PermissionRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PermissionServiceImpl implements PermissionService {

  private final PermissionRepository permissionRepository;

  public PermissionServiceImpl(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  @Override
  public Permission createPermission(@RequestBody @Valid String permissionName) {
    if(permissionName == null) {
      throw new IllegalArgumentException("Permission cannot be null");
    }
    Permission permission = new Permission();
    permission.setName(permissionName);
    return permissionRepository.save(permission);
  }

  @Override
  public Optional<Permission> getPermissionByName(String permissionName) {
    if(permissionName == null) {
      throw new IllegalArgumentException("Permission name cannot be null");
    }
    return permissionRepository.findByName(permissionName);

  }

  @Override
  public Optional<Permission> getPermissionById(UUID id) {
    if(id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
   return permissionRepository.findById(id);
  }

  @Override
  public void deletePermission(UUID id) {
    if(id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
   permissionRepository.deleteById(id);

  }

  @Override
  public List<Permission> getAllPermissions() {
    return permissionRepository.findAll();
  }
}
