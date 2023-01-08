package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    VehicleChangeRequest insertVehicleChangeRequest(VehicleChangeRequest vehicleChangeRequest);
    Page<VehicleChangeRequest> getAllVehicleChangeRequests(Pageable page);
    void acceptVehicleChangeRequest(Integer requestId);
    void rejectVehicleChangeRequest(Integer requestId);
}
