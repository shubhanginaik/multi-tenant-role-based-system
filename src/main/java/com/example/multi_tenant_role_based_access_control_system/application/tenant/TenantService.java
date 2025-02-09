package com.example.multi_tenant_role_based_access_control_system.application.tenant;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Tenant;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class TenantService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TenantRepository tenantRepository;

  @Transactional
  public Tenant createTenant(String tenantName) {
    String schemaName = "tenant_" + tenantName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "_"); // Prevent SQL injection

    // Check if schema already exists
    //noinspection SqlNoDataSourceInspection
    String checkSchemaQuery = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
    Integer count = jdbcTemplate.queryForObject(checkSchemaQuery, new Object[]{schemaName}, Integer.class);

    if (count != null && count > 0) {
      throw new RuntimeException("Schema already exists for tenant: " + tenantName);
    }

    // Create schema if not exists
    try {
      jdbcTemplate.execute("CREATE SCHEMA " + schemaName);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create schema: " + e.getMessage());
    }

    // Create Tenant Record
    Tenant tenant = new Tenant();
    tenant.setName(tenantName);
    tenant.setSchemaName(schemaName);
    return tenantRepository.save(tenant);
  }
}
