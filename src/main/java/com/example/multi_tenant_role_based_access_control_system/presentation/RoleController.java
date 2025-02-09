package com.example.multi_tenant_role_based_access_control_system.presentation;

import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.RoleService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping
  public Role createRole(Role role) {
    return roleService.createRole(role);
  }

  @GetMapping
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }

  @GetMapping("/{roleName}")
  public Role getRoleByName(@PathVariable String roleName) {
    return roleService.getRoleByName(roleName);
  }

  @GetMapping("/{id}")
  public Role getRoleById(@PathVariable UUID id) {
    return roleService.getRoleById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteRole(@PathVariable UUID id) {
    roleService.deleteRole(id);
  }

}
