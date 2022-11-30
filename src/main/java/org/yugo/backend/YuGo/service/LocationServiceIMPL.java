package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

public class LocationServiceIMPL implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceIMPL(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Override
    public Location add(Location location){
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> get(Integer id) {
        return locationRepository.findById(id);
    }
}
