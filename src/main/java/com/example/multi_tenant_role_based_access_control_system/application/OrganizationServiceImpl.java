package com.example.multi_tenant_role_based_access_control_system.application;

import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.OrganizationService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Organization;
import com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories.OrganizationRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository organizationRepository;

  public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Organization createOrganization(Organization organization) {
    if(organization == null) {
      throw new IllegalArgumentException("Organization cannot be null");
    }
    if(organization.getName() == null) {
      throw new IllegalArgumentException("Organization name cannot be null");
    }

    return organizationRepository.save(organization);
  }

  @Override
  public Optional<Organization> getOrganizationByName(String organizationName) {
    return organizationRepository.findByName(organizationName);
  }

  @Override
  public Optional<Organization> getOrganizationById(UUID id) {
    return organizationRepository.findById(id);
  }

  @Override
  public void deleteOrganization(UUID id) {
    organizationRepository.deleteById(id);
  }

  @Override
  public List<Organization> getAllOrganizations() {
    return organizationRepository.findAll();
  }

}
