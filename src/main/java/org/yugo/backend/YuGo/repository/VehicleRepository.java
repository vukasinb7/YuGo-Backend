package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
