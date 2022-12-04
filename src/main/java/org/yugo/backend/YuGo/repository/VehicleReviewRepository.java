package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;

public interface VehicleReviewRepository extends JpaRepository<VehicleReview,Integer> {

    @Query(value = "SELECT * FROM VEHICLE_REVIEWS vr WHERE vr.vehicle_id = :vehicle_id", nativeQuery = true)
    List<VehicleReview> findReviewsByVehicle(@Param("vehicle_id") Integer id);
}
