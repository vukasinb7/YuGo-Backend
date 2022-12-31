package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String role);
}
