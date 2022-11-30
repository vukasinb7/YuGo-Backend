package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleType;

import java.util.List;

public interface VehicleService {
    /* =========================== Vehicle =========================== */
    Vehicle addVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(Integer id);

    /* =========================== VehicleType =========================== */
    VehicleType addVehicleType(VehicleType vehicleType);

    List<VehicleType> getAllVehicleTypes();

    VehicleType getVehicleType(Integer id);
}
