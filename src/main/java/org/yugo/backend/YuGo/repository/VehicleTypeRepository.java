package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.VehicleCategoryPrice;

public interface VehicleTypeRepository extends JpaRepository<VehicleCategoryPrice,Integer> {
}
