package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleTypePrice;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.VehicleTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    /* =========================== Vehicle =========================== */
    @Override
    public Vehicle insertVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Override public Vehicle updateVehicle(Vehicle vehicle){
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicle.getId());
        if(vehicleOpt.isEmpty()){
            return null;
        }
        return vehicleRepository.save(vehicle);
    }
    @Override
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicle(Integer id){
        return vehicleRepository.findById(id).orElse(null);
    }

    /* =========================== VehicleType =========================== */
    @Override
    public VehicleTypePrice insertVehicleType(VehicleTypePrice vehicleTypePrice){
        return vehicleTypeRepository.save(vehicleTypePrice);
    }

    @Override
    public List<VehicleTypePrice> getAllVehicleTypes(){
        return vehicleTypeRepository.findAll();
    }

    @Override
    public VehicleTypePrice getVehicleType(Integer id){
        return vehicleTypeRepository.findById(id).orElse(null);
    }
}
