package com.example.multi_tenant_role_based_access_control_system.infrastructure.JpaReposotories;


import com.example.multi_tenant_role_based_access_control_system.domain.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByUsername(String username);
  User findByEmail(String email);
}
