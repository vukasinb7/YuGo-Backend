package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;
import org.yugo.backend.YuGo.model.VehicleTypePrice;
import org.yugo.backend.YuGo.repository.VehicleChangeRequestRepository;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.VehicleTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleChangeRequestRepository vehicleChangeRequestRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository,
                              VehicleChangeRequestRepository vehicleChangeRequestRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.vehicleChangeRequestRepository = vehicleChangeRequestRepository;
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
        Optional<Vehicle> vehicle= vehicleRepository.findById(id);
        if (vehicle.isEmpty())
            throw new NotFoundException("Vehicle does not exist!");
        getVehiclesDriver(id);
        return vehicle.get();
    }
    public Integer getVehiclesDriver(Integer id){
        Optional<Integer> driver= vehicleRepository.findDriverByVehicleId(id);
        if (driver.isEmpty())
            throw new BadRequestException("Vehicle is not assigned to the specific driver!");
        return driver.get();
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
    @Override
    public VehicleTypePrice getVehicleTypeByName(String name){ return vehicleTypeRepository.findByType(name);}


    /* =========================== VehicleChangeRequest =========================== */
    @Override
    public VehicleChangeRequest insertVehicleChangeRequest(VehicleChangeRequest vehicleChangeRequest){
        return vehicleChangeRequestRepository.save(vehicleChangeRequest);
    }

    @Override
    public List<VehicleChangeRequest> getALlVehicleChangeRequests(){
        return vehicleChangeRequestRepository.findAll();
    }
}
