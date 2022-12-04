package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Ride;

import java.util.List;
import java.util.Optional;

public interface RideService {
    Ride save(Ride ride);

    List<Ride> getAll();

    Optional<Ride> get(Integer id);

    public Ride getActiveRideByDriver(Integer id);

    public Ride getActiveRideByPassenger(Integer id);

    public Ride insert(Ride ride);
}
