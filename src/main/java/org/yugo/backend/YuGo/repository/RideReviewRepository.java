package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;

public interface RideReviewRepository extends JpaRepository<RideReview,Integer> {

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.driver_id = :driver_id", nativeQuery = true)
    List<RideReview> findReviewsByDriver(@Param("driver_id") Integer id);
}
