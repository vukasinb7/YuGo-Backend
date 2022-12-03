package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PassengerService {
    User insert(User user);
    List<User> getAll();
    Page<Ride> getPassengerRidesPage(Integer passengerID, LocalDateTime from, LocalDateTime to, Pageable page);
    Page<User> getPassengersPage(Pageable page);
    Optional<User> get(Integer id);
}
