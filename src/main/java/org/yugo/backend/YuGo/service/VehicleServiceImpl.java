package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleCategoryPrice;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.VehicleTypeRepository;

import java.util.List;

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
    public Vehicle saveVehicle(Vehicle vehicle){
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
    public VehicleCategoryPrice saveVehicleType(VehicleCategoryPrice vehicleCategoryPrice){
        return vehicleTypeRepository.save(vehicleCategoryPrice);
    }

    @Override
    public List<VehicleCategoryPrice> getAllVehicleTypes(){
        return vehicleTypeRepository.findAll();
    }

    @Override
    public VehicleCategoryPrice getVehicleType(Integer id){
        return vehicleTypeRepository.findById(id).orElse(null);
    }
}
