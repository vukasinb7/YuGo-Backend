package org.yugo.backend.YuGo.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    @Query(value = "select driver_id from vehicles where id=:vehicleId",nativeQuery = true)
    Optional<Integer> findDriverByVehicleId(@Param("vehicleId") Integer vehicleId);
}
