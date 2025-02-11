package com.example.multi_tenant_role_based_access_control_system.config;

import com.example.multi_tenant_role_based_access_control_system.application.RolePermissionsServiceImpl;
import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantService;
import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.PermissionService;
import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.RoleService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.PermissionRepository;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.RoleRepository;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.TenantRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final TenantService tenantService;
  private RoleService roleService;
  private final PermissionService permissionService;
  private final RolePermissionsServiceImpl rolePermissionsService;
  private final TenantRepository tenantRepository;
  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;


  public DataLoader(
      TenantService tenantService,
      RoleService roleService,
      PermissionService permissionService,
      RolePermissionsServiceImpl rolePermissionsService,
      TenantRepository tenantRepository,
      RoleRepository roleRepository,
      PermissionRepository permissionRepository) {
    this.tenantService = tenantService;
    this.roleService = roleService;
    this.permissionService = permissionService;
    this.rolePermissionsService = rolePermissionsService;
    this.tenantRepository = tenantRepository;
    this.roleRepository = roleRepository;
    this.permissionRepository = permissionRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    try {
    if(tenantRepository.findByName("tenant1") == null){
      Tenant tenant1 = tenantService.createTenant("tenant1");
      System.out.println("Tenant created: " + tenant1.getName());
    }

    if(tenantRepository.findByName("tenant2") == null){
      Tenant tenant2 = tenantService.createTenant("tenant2");
      System.out.println("Tenant created: " + tenant2.getName());
    }

    //default permissions
    Permission readPermission = permissionRepository.findByName("READ")
        .orElseGet(() -> permissionService.createPermission("READ"));

    Permission writePermission = permissionRepository.findByName("WRITE")
        .orElseGet(() -> permissionService.createPermission("WRITE"));

    Set<Permission> adminPermissions = new HashSet<>();
    adminPermissions.add(readPermission);
    adminPermissions.add(writePermission);

    Set<Permission> userPermissions = new HashSet<>();
    userPermissions.add(readPermission);

    //default roles
    Role adminRole = roleRepository.findByName("ADMIN");
    if (adminRole == null) {
      adminRole = roleService.createRole("ADMIN");
    }

    Role userRole = roleRepository.findByName("USER");
    userRole = roleService.createRole("USER");

    System.out.println("Roles created: " + adminRole.getName() + ", " + userRole.getName());

    if (adminRole.getRolePermissions().isEmpty()) {
      rolePermissionsService.assignPermissionsToRole(adminRole, adminPermissions);
      System.out.println("Assigned Permissions to ADMIN Role.");
    } else {
      System.out.println("ADMIN Role already has permissions.");
    }

    if (userRole.getRolePermissions().isEmpty()) {
      rolePermissionsService.assignPermissionsToRole(userRole, userPermissions);
      System.out.println("Assigned Permissions to USER Role.");
    } else {
      System.out.println("USER Role already has permissions.");
    }
  } catch (Exception e){
    System.out.println("Error: " + e.getMessage());
  }
  }
}
