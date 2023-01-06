package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;
import org.yugo.backend.YuGo.model.VehicleTypePrice;

import java.util.List;

public interface VehicleService {
    /* =========================== Vehicle =========================== */
    Vehicle insertVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicle(Integer id);
    Integer getVehiclesDriver(Integer id);
    Vehicle updateVehicle(Vehicle vehicle);
    /* =========================== VehicleType =========================== */
    VehicleTypePrice insertVehicleType(VehicleTypePrice vehicleTypePrice);
    List<VehicleTypePrice> getAllVehicleTypes();
    VehicleTypePrice getVehicleType(Integer id);
    public VehicleTypePrice getVehicleTypeByName(String name);

    /* =========================== VehicleChangeRequest =========================== */
    public VehicleChangeRequest insertVehicleChangeRequest(VehicleChangeRequest vehicleChangeRequest);
}
