package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleTypePrice;

import java.util.List;

public interface VehicleService {
    /* =========================== Vehicle =========================== */
    Vehicle insertVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(Integer id);
    Vehicle updateVehicle(Vehicle vehicle);
    /* =========================== VehicleType =========================== */
    VehicleTypePrice insertVehicleType(VehicleTypePrice vehicleTypePrice);

    List<VehicleTypePrice> getAllVehicleTypes();

    VehicleTypePrice getVehicleType(Integer id);

    double calculatePrice(Integer vehicleTypePriceId, Location from, Location to);
}
