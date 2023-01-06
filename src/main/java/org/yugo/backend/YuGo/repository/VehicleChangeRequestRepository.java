package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

public interface VehicleChangeRequestRepository extends JpaRepository<VehicleChangeRequest,Integer> {
}
