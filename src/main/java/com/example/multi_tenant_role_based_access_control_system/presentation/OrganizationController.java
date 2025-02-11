package com.example.multi_tenant_role_based_access_control_system.presentation;

import com.example.multi_tenant_role_based_access_control_system.domain.abstraction.OrganizationService;
import com.example.multi_tenant_role_based_access_control_system.domain.entity.Organization;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }


  @PostMapping
  public Organization createOrganization(String organizationName, String organizationDescription) {
    if(organizationName == null) {
      throw new IllegalArgumentException("Organization name and description cannot be null");
    }
    Organization organization = new Organization();
    organization.setName(organizationName);
    if(organizationDescription == null) {
      organizationDescription = "";
    }
    organization.setDescription(organizationDescription);
    return organizationService.createOrganization(organization);
  }

  @GetMapping
  public List<Organization> getAllOrganizations() {
    return organizationService.getAllOrganizations();
  }

  @GetMapping("/name")
  public Optional<Organization> getOrganizationByName(String organizationName) {
    if(organizationName == null) {
      throw new IllegalArgumentException("Organization name cannot be null");
    }
    return organizationService.getOrganizationByName(organizationName);
  }

  @GetMapping("/id")
  public Optional<Organization> getOrganizationById(UUID id) {
    if(id == null) {
      throw new IllegalArgumentException("Organization id cannot be null");
    }
    return organizationService.getOrganizationById(id);
  }

  @DeleteMapping("/id")
  public void deleteOrganization(UUID id) {
    if(id == null) {
      throw new IllegalArgumentException("Organization id cannot be null");
    }
    organizationService.deleteOrganization(id);
  }

}
