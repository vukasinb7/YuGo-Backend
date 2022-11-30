package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleCategoryPrice;

import java.util.List;

public interface VehicleService {
    /* =========================== Vehicle =========================== */
    Vehicle saveVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(Integer id);

    /* =========================== VehicleType =========================== */
    VehicleCategoryPrice addVehicleType(VehicleCategoryPrice vehicleCategoryPrice);

    List<VehicleCategoryPrice> getAllVehicleTypes();

    VehicleCategoryPrice getVehicleType(Integer id);
}
