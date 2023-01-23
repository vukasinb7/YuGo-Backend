package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yugo.backend.YuGo.model.Panic;

import java.util.Optional;

public interface PanicRepository extends JpaRepository<Panic,Integer> {
    @Query(value = "SELECT p FROM Panic p ORDER BY p.time DESC")
    Page<Panic> findAllPanics(Pageable page);

    Optional<Panic> findByRideId(Integer rideId);
}
