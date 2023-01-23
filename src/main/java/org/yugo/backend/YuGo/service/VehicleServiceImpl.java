package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.repository.RideRepository;
import org.yugo.backend.YuGo.repository.VehicleChangeRequestRepository;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.VehicleTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final RideRepository rideRepository;
    private final VehicleChangeRequestRepository vehicleChangeRequestRepository;
    private final DriverService driverService;

    private final WebSocketService webSocketService;
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository,
                              RideRepository rideRepository, VehicleChangeRequestRepository vehicleChangeRequestRepository,
                              DriverService driverService, WebSocketService webSocketService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.rideRepository = rideRepository;
        this.vehicleChangeRequestRepository = vehicleChangeRequestRepository;
        this.driverService = driverService;
        this.webSocketService = webSocketService;
    }

    /* =========================== Vehicle =========================== */
    @Override
    public Vehicle insertVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicleLocation(Location location, Integer vehicleID){
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleID);
        if(vehicleOpt.isEmpty()){
            throw new NotFoundException("Vehicle with given id doesn't exist!");
        }
        Vehicle vehicle = vehicleOpt.get();
        vehicle.setCurrentLocation(location);
        Optional<Ride> rideOpt = rideRepository.getStartedRideByVehicle(vehicleID);
        if(rideOpt.isPresent()){
            for(Passenger passenger : rideOpt.get().getPassengers()){
                webSocketService.notifyPassengerAboutVehicleLocation(passenger.getId(), location.getLongitude(), location.getLatitude());
            }
        }

        vehicleRepository.save(vehicle);
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
    public VehicleTypePrice getVehicleTypeByName(String name){
        return vehicleTypeRepository.findByType(name);
    }


    /* =========================== VehicleChangeRequest =========================== */
    @Override
    public VehicleChangeRequest insertVehicleChangeRequest(VehicleChangeRequest vehicleChangeRequest){
        return vehicleChangeRequestRepository.save(vehicleChangeRequest);
    }

    @Override
    public Page<VehicleChangeRequest> getAllVehicleChangeRequests(Pageable page){
        return vehicleChangeRequestRepository.findAllVehicleChangeRequests(page);
    }

    @Override
    public void acceptVehicleChangeRequest(Integer requestId){
        Optional<VehicleChangeRequest> vehicleChangeRequestOptional = vehicleChangeRequestRepository.findById(requestId);
        if (vehicleChangeRequestOptional.isPresent()){
            VehicleChangeRequest vehicleChangeRequest = vehicleChangeRequestOptional.get();
            driverService.updateDriverVehicle(vehicleChangeRequest.getDriver().getId(),
                    vehicleChangeRequest.getVehicle());
            vehicleChangeRequestRepository.rejectDriversVehicleChangeRequests(vehicleChangeRequest.getDriver().getId());
            vehicleChangeRequest.setStatus(VehicleChangeRequestStatus.ACCEPTED);
            vehicleChangeRequestRepository.save(vehicleChangeRequest);
        }
        else {
            throw new NotFoundException("Vehicle change request not found!");
        }
    }

    @Override
    public void rejectVehicleChangeRequest(Integer requestId){
        Optional<VehicleChangeRequest> vehicleChangeRequestOptional = vehicleChangeRequestRepository.findById(requestId);
        if (vehicleChangeRequestOptional.isPresent()){
            VehicleChangeRequest vehicleChangeRequest = vehicleChangeRequestOptional.get();
            vehicleChangeRequest.setStatus(VehicleChangeRequestStatus.REJECTED);
            vehicleChangeRequestRepository.save(vehicleChangeRequest);
        }
        else {
            throw new NotFoundException("Vehicle change request not found!");
        }
    }
}
