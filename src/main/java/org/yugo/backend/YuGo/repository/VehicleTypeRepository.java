package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.VehicleType;
import org.yugo.backend.YuGo.model.VehicleTypePrice;

public interface VehicleTypeRepository extends JpaRepository<VehicleTypePrice,Integer> {

    @Query(value = "select * from vehicle_type_prices where vehicle_type=:vehicle_name", nativeQuery = true)
    public VehicleTypePrice findByType(@Param("vehicle_name")String vehicle_name);
}
