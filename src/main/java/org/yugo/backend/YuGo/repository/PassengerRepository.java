package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
}
