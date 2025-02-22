package com.example.multi_tenant_role_based_access_control_system.application;

import com.example.multi_tenant_role_based_access_control_system.application.service.RoleService;
import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantContext;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Role;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.RoleRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role createRole(String roleName){
    Role newRole = new Role();
    newRole.setName(roleName);
    return  roleRepository.save(newRole);
  }

  @Override
  public List<Role> getAllRoles(){
    String tenant = TenantContext.getCurrentTenant();
    System.out.println("Fetching roles for tenant: " + tenant);
    return roleRepository.findAll();
  }

  @Override
  public Role getRoleByName(String roleName){
    return roleRepository.findByName(roleName);
  }

  @Override
  public Role getRoleById(UUID id){
    return roleRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteRole(UUID id){
    roleRepository.deleteById(id);
  }
}
