package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType,Integer> {
}
