package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Passenger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PassengerService {
    Passenger insert(Passenger passenger);
    Passenger update(Passenger passengerUpdate);
    List<Passenger> getAll();
    Page<Passenger> getPassengersPage(Pageable page);
    Optional<Passenger> get(Integer id);
}
