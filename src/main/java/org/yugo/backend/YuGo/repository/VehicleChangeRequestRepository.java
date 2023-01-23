package org.yugo.backend.YuGo.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

public interface VehicleChangeRequestRepository extends JpaRepository<VehicleChangeRequest,Integer> {
    @Query(value = "SELECT r FROM VehicleChangeRequest r WHERE r.status = " +
            "org.yugo.backend.YuGo.model.VehicleChangeRequestStatus.PENDING")
    Page<VehicleChangeRequest> findAllVehicleChangeRequests(Pageable page);
    @Transactional
    @Modifying
    @Query(value = "UPDATE VehicleChangeRequest r SET " +
            "r.status = org.yugo.backend.YuGo.model.VehicleChangeRequestStatus.REJECTED WHERE r.driver.id = :driverId " +
            "AND r.status = org.yugo.backend.YuGo.model.VehicleChangeRequestStatus.PENDING")
    void rejectDriversVehicleChangeRequests(@Param("driverId") Integer driverId);
}
