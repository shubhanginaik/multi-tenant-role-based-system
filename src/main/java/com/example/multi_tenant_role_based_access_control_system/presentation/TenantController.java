package com.example.multi_tenant_role_based_access_control_system.presentation;

import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {

  @Autowired
  private TenantService tenantService;

  @PostMapping
  public ResponseEntity<Tenant> createTenant(@RequestParam String tenantName) {
    Tenant tenant = tenantService.createTenant(tenantName);
    return ResponseEntity.ok(tenant);
  }

  @GetMapping
  public ResponseEntity<List<Tenant>> getAllTenants() {
    return ResponseEntity.ok(tenantService.getAllTenants());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) {
    Optional<Tenant> tenant = tenantService.getTenantById(id);
    return tenant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
    tenantService.deleteTenant(id);
    return ResponseEntity.noContent().build();
  }
}
