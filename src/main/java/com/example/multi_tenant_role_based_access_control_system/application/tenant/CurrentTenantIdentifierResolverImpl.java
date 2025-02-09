package com.example.multi_tenant_role_based_access_control_system.application.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

  @Override
  public String resolveCurrentTenantIdentifier() {
    String tenantId = TenantContext.getCurrentTenant();
    return (tenantId != null) ? tenantId : "public"; // Default to 'public' schema
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
