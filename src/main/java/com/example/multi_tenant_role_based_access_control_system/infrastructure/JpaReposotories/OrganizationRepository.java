package com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories;

import com.example.multi_tenant_role_based_access_control_system.domain.entity.Organization;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
  Optional<Organization> findByName(String organizationName);

}
