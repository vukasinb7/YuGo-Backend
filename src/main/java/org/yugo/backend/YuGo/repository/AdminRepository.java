package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
