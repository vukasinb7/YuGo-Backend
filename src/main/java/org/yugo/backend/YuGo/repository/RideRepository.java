package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    @Query(value = "SELECT * FROM RIDES r WHERE r.driver_id = :did and r.status='ACTIVE'",
            nativeQuery = true)
    public Ride getActiveRideByDriver(@Param("did") Integer did);
}
