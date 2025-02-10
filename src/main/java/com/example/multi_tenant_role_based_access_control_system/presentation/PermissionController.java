package com.example.multi_tenant_role_based_access_control_system.presentation;

import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.PermissionService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

  private final PermissionService permissionService;

  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PostMapping
  public ResponseEntity<Permission> createPermission(@RequestBody @Valid Permission permission) {
    Permission createdPermission = permissionService.createPermission(permission);
    return ResponseEntity.ok(createdPermission);
  }

  @GetMapping
  public ResponseEntity<Iterable<Permission>> getAllPermissions() {
    return ResponseEntity.ok(permissionService.getAllPermissions());
  }

  @GetMapping("/name")
  public ResponseEntity<Optional<Permission>> getPermissionByName(String permissionName) {
    return ResponseEntity.ok(permissionService.getPermissionByName(permissionName));
  }

  @GetMapping("/id")
  public ResponseEntity<Optional<Permission>> getPermissionById(UUID id) {
    return ResponseEntity.ok(permissionService.getPermissionById(id));
  }

  @DeleteMapping("/id")
  public ResponseEntity<Void> deletePermission(UUID id) {
    permissionService.deletePermission(id);
    return ResponseEntity.noContent().build();
  }
}
