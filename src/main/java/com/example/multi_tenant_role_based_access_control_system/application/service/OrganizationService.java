package com.example.multi_tenant_role_based_access_control_system.application.service;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Organization;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationService {
  Organization createOrganization(Organization organization);

  Optional<Organization> getOrganizationByName(String organizationName);

  Optional<Organization> getOrganizationById(UUID id);

  void deleteOrganization(UUID id);

  List<Organization> getAllOrganizations();

}
