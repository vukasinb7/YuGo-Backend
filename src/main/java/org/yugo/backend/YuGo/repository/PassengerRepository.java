package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
    @Query(value = "SELECT p FROM Passenger p")
    public List<Passenger> findAllPassengers();

    @Query(value = "SELECT p FROM Passenger p")
    public Page<Passenger> findAllPassengers(Pageable page);
}
