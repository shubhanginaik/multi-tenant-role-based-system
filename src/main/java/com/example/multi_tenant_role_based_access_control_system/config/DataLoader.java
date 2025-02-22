//package com.example.multi_tenant_role_based_access_control_system.config;
//
//import com.example.multi_tenant_role_based_access_control_system.application.RolePermissionsServiceImpl;
//import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantContext;
//import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantService;
//import com.example.multi_tenant_role_based_access_control_system.application.service.PermissionService;
//import com.example.multi_tenant_role_based_access_control_system.application.service.RoleService;
//import com.example.multi_tenant_role_based_access_control_system.domain.entity.Permission;
//import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
//import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
//import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.PermissionRepository;
//import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.RoleRepository;
//import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.TenantRepository;
//import java.util.HashSet;
//import java.util.Set;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//  private final TenantService tenantService;
//  private RoleService roleService;
//  private final PermissionService permissionService;
//  private final RolePermissionsServiceImpl rolePermissionsService;
//  private final TenantRepository tenantRepository;
//  private final RoleRepository roleRepository;
//  private final PermissionRepository permissionRepository;
//
//
//  public DataLoader(
//      TenantService tenantService,
//      RoleService roleService,
//      PermissionService permissionService,
//      RolePermissionsServiceImpl rolePermissionsService,
//      TenantRepository tenantRepository,
//      RoleRepository roleRepository,
//      PermissionRepository permissionRepository) {
//    this.tenantService = tenantService;
//    this.roleService = roleService;
//    this.permissionService = permissionService;
//    this.rolePermissionsService = rolePermissionsService;
//    this.tenantRepository = tenantRepository;
//    this.roleRepository = roleRepository;
//    this.permissionRepository = permissionRepository;
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    try {
//      Tenant tenant1 = tenantRepository.findByName("tenant1");
//    if(tenant1 == null){
//      tenant1 = tenantService.createTenant("tenant1");
//      System.out.println("Tenant created: " + tenant1.getName());
//    }
//      TenantContext.setCurrentTenant(tenant1.getSchemaName());
//      loadTenantData(tenant1.getSchemaName());
//
//      Tenant tenant2 = tenantRepository.findByName("tenant2");
//      if (tenant2 == null) {
//        tenant2 = tenantService.createTenant("tenant2");
//        System.out.println("Tenant created: " + tenant2.getName());
//      }
//
//      // 🔹 Set schema before loading data for tenant2
//      TenantContext.setCurrentTenant(tenant2.getSchemaName());
//      loadTenantData(tenant2.getSchemaName());
//      //default permissions
//
//  } catch (Exception e){
//    System.out.println("Error: " + e.getMessage());
//  }
//  }
//
//  private void loadTenantData(String tenantName) {
//    System.out.println("Loading data for tenant: " + tenantName);
//
//    TenantContext.setCurrentTenant(tenantName);
//
//
//    Permission readPermission = permissionRepository.findByName("READ")
//        .orElseGet(() -> permissionService.createPermission("READ"));
//
//    Permission writePermission = permissionRepository.findByName("WRITE")
//        .orElseGet(() -> permissionService.createPermission("WRITE"));
//
//    Set<Permission> adminPermissions = new HashSet<>();
//    adminPermissions.add(readPermission);
//    adminPermissions.add(writePermission);
//
//    Set<Permission> userPermissions = new HashSet<>();
//    userPermissions.add(readPermission);
//
//    //default roles
//    Role adminRole = roleRepository.findByName("ADMIN");
//    if (adminRole == null){
//      adminRole = roleService.createRole("ADMIN");
//    }
//
//    Role userRole = roleRepository.findByName("USER");
//    if (userRole == null){
//      userRole = roleService.createRole("USER");
//    }
//    rolePermissionsService.assignPermissionsToRole(adminRole, adminPermissions);
//    System.out.println("Assigned Permissions to ADMIN Role.");
//
//
//    if (userRole.getRolePermissions().isEmpty()) {
//      rolePermissionsService.assignPermissionsToRole(userRole, userPermissions);
//      System.out.println("Assigned Permissions to USER Role.");
//    } else {
//      System.out.println("USER Role already has permissions.");
//    }
//  }
//}
