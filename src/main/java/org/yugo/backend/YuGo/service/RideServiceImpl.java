package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;
import org.yugo.backend.YuGo.dto.LocationInOut;
import org.yugo.backend.YuGo.dto.PathInOut;
import org.yugo.backend.YuGo.dto.RideIn;
import org.yugo.backend.YuGo.dto.RouteProperties;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.VehicleType;
import org.yugo.backend.YuGo.repository.RideRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RoutingService routingService;
    private final DriverService driverService;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, RoutingService routingService, DriverService driverService){
        this.rideRepository = rideRepository;
        this.routingService = routingService;
        this.driverService = driverService;
    }

    @Override
    public Ride insert(Ride ride){
        return rideRepository.save(ride);
    }

    @Override
    public Ride createRide(RideIn rideIn) throws Exception {
        PathInOut path = rideIn.getLocations().get(0);
        List<Driver> availableDrivers = driverService.getDriversInRange(path.getDeparture().getLatitude(), path.getDeparture().getLongitude(), 10000);
        if(availableDrivers.isEmpty()){
            throw new Exception("There are no available drivers at the moment");
        }
        availableDrivers.removeIf(driver -> !driver.isActive());
        if(availableDrivers.isEmpty()){
            throw new Exception("There are no available drivers at the moment");
        }
        VehicleType vehicleType = EnumUtils.findEnumInsensitiveCase(VehicleType.class, rideIn.getVehicleType());
        availableDrivers.removeIf(driver -> driver.getVehicle().getVehicleType() != vehicleType ||
                (!driver.getVehicle().getAreBabiesAllowed() && rideIn.isBabyTransport()) ||
                (!driver.getVehicle().getArePetsAllowed() && rideIn.isPetTransport()));// TODO proveriti da li vozilo ima dovoljno sedista
        if(availableDrivers.isEmpty()){
            throw new Exception("There are no available drivers at the moment");
        }
        // TODO proveriti da li vozaci iz lista imaju zakaznu voznju

        RouteProperties routeProperties = getRouteProperties(rideIn);   // contains ride distance (in meters) and estimated duration (in seconds)


        return null;
    }
    private RouteProperties getRouteProperties(RideIn rideIn){
        double fromLat = rideIn.getLocations().get(0).getDeparture().getLatitude();
        double fromLng = rideIn.getLocations().get(0).getDeparture().getLongitude();
        double toLat = rideIn.getLocations().get(0).getDestination().getLatitude();
        double toLng = rideIn.getLocations().get(0).getDestination().getLongitude();;
        return routingService.getRouteProperties(fromLat, fromLng, toLat, toLng);
    }
    @Override
    public List<Ride> getAll() {
        return rideRepository.findAll();
    }

    @Override
    public Optional<Ride> get(Integer id) {
        return rideRepository.findById(id);
    }

    @Override
    public Ride getActiveRideByDriver(Integer id){ return rideRepository.findActiveRideByDriver(id);}

    @Override
    public Ride getActiveRideByPassenger(Integer id){ return rideRepository.findActiveRideByPassenger(id);}

    public Page<Ride> getPassengerRides(Integer passengerId, LocalDateTime from, LocalDateTime to, Pageable page){
        return rideRepository.findRidesByPassenger(passengerId, from, to, page);
    }

    public Page<Ride> getUserRides(Integer userId, LocalDateTime from, LocalDateTime to, Pageable page){
        return rideRepository.findRidesByUser(userId, from, to, page);
    }

    public Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        return rideRepository.findRidesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }
}
