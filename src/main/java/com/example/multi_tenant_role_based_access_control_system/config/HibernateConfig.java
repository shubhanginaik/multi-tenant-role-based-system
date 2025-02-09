package com.example.multi_tenant_role_based_access_control_system.config;

import static org.hibernate.cfg.AvailableSettings.*;
import com.example.multi_tenant_role_based_access_control_system.application.tenant.SchemaMultiTenantConnectionProvider;
import com.example.multi_tenant_role_based_access_control_system.application.tenant.CurrentTenantIdentifierResolverImpl;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HibernateConfig {

  @Bean
  public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource dataSource) {
    return new SchemaMultiTenantConnectionProvider(dataSource);
  }

  @Bean
  @Primary
  public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
    return new CurrentTenantIdentifierResolverImpl();
  }

  @Bean
  public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(
      MultiTenantConnectionProvider connectionProvider,
      CurrentTenantIdentifierResolver tenantIdentifierResolver) {

    return properties -> {

      properties.put("hibernate.multiTenancy", "SCHEMA");
      properties.put(MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
      properties.put(MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
    };
  }
}