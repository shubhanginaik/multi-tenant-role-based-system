package com.example.multi_tenant_role_based_access_control_system.config;

import com.example.multi_tenant_role_based_access_control_system.application.tenant.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import jakarta.servlet.Filter;

@Component
public class TenantFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String tenant = httpRequest.getHeader("X-Tenant-ID");  // Read Tenant from Header
    System.out.println("TenantFilter: " + tenant);

    if (tenant != null) {
      TenantContext.setCurrentTenant(tenant);
    }

    try {
      chain.doFilter(request, response);
    } catch (ServletException e) {
      throw new RuntimeException(e);
    } finally {
      TenantContext.clear();  // Clean up after request
    }
  }
}

