package com.example.multi_tenant_role_based_access_control_system.application.tenant;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TenantRepository tenantRepository;

  @Transactional
  public Tenant createTenant(String tenantName) {
    String schemaName = "tenant_" + tenantName.toLowerCase();

    // Create schema if not exists
    jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);

    // Create Tenant Record
    Tenant tenant = new Tenant();
    tenant.setName(tenantName);
    tenant.setSchemaName(schemaName);
    return tenantRepository.save(tenant);
  }

  public List<Tenant> getAllTenants() {
    return tenantRepository.findAll();
  }

  public Optional<Tenant> getTenantById(Long id) {
    return tenantRepository.findById(id);
  }

  @Transactional
  public void deleteTenant(Long id) {
    tenantRepository.deleteById(id);
  }
}
