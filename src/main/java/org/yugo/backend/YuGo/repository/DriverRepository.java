package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Driver;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
