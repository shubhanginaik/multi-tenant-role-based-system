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
  public Tenant createTenant(String name) {
//    String name = new org.json.JSONObject(nameJson).getString("name");
    String schemaName = "tenant_" + name.toLowerCase().replaceAll("[^a-z0-9_]", "");
    jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);

    // 2. Switch to tenant schema
    jdbcTemplate.execute("SET search_path TO " + schemaName);

    // 3. Force Hibernate to create tables in the new schema
    createTablesForTenant(schemaName);

    // 4. Save tenant info in DB
    Tenant tenant = new Tenant();
    tenant.setName(name);
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

  // Force Hibernate to create tables in the new schema
  private void createTablesForTenant(String schemaName) {
    jdbcTemplate.execute("SET search_path TO " + schemaName);

    // 🔹 Create roles table
    jdbcTemplate.execute("""
        CREATE TABLE IF NOT EXISTS roles (
             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
             name VARCHAR(255) NOT NULL,
             description TEXT
        )
    """);

    // 🔹 Create permissions table
    jdbcTemplate.execute("""
        CREATE TABLE IF NOT EXISTS permissions (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            name VARCHAR(255) NOT NULL
        )
    """);
  }
}
