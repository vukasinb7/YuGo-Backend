package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.*;
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
    private final UserService userService;
    private final PassengerService passengerService;
    @Autowired
    public RideServiceImpl(RideRepository rideRepository, RoutingService routingService, DriverService driverService, UserService userService, PassengerService passengerService){
        this.rideRepository = rideRepository;
        this.routingService = routingService;
        this.driverService = driverService;
        this.userService = userService;
        this.passengerService=passengerService;
    }

    @Override
    public Ride insert(Ride ride){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (rideRepository.findPendingRidesByUser(user.getId())!=null)
            throw new BadRequestException("Cannot create a ride while you have one already pending!");
        return rideRepository.save(ride);
    }

    @Override
    public Ride save(Ride ride){
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
    public Ride get(Integer id) {
        Optional<Ride> ride=  rideRepository.findById(id);
        if (ride.isEmpty())
            throw new NotFoundException("Ride does not exist!");
        return ride.get();
    }

    @Override
    public Ride getActiveRideByDriver(Integer id){
        Optional<Ride> ride=  rideRepository.findActiveRideByDriver(id);
        if (ride.isEmpty())
            throw new NotFoundException("Active ride does not exist!");
        return ride.get();
    }

    @Override
    public Ride getActiveRideByPassenger(Integer id){
        Optional<Ride> ride=  rideRepository.findActiveRideByPassenger(id);
        if (ride.isEmpty())
            throw new NotFoundException("Active ride does not exist!");
        return ride.get();
    }
    @Override
    public Page<Ride> getPassengerRides(Integer passengerId, LocalDateTime from, LocalDateTime to, Pageable page){
        passengerService.get(passengerId);
        return rideRepository.findRidesByPassenger(passengerId, from, to, page);
    }
    @Override
    public List<Ride> getPassengerRidesNonPagable(Integer passengerId, LocalDateTime from, LocalDateTime to){
        passengerService.get(passengerId);
        return rideRepository.findRidesByPassenger(passengerId, from, to);
    }
    @Override
    public Page<Ride> getUserRides(Integer userId, LocalDateTime from, LocalDateTime to, Pageable page){
        userService.getUser(userId);
        return rideRepository.findRidesByUser(userId, from, to, page);
    }

    public Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        return rideRepository.findRidesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }
    @Override
    public List<Ride> getRidesByDriverNonPageable(Integer driverId, LocalDateTime start, LocalDateTime end){
        driverService.getDriver(driverId);
        return rideRepository.findRidesByDriverAndStartTimeAndEndTimePageable(driverId, start, end);
    }

    public Ride cancelRide(Integer id){
        Ride ride =get(id);
        if (ride.getStatus()== RideStatus.ACTIVE || ride.getStatus()==RideStatus.PENDING) {
            ride.setStatus(RideStatus.CANCELED);
            save(ride);
        }
        else{
            throw new BadRequestException("Cannot cancel a ride that is not in status PENDING or STARTED!");
        }
        return ride;
    }

    public Ride startRide(Integer id){
        Ride ride =get(id);
        if (ride.getStatus()== RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.ACTIVE);
            save(ride);
        }
        else{
            throw new BadRequestException("Cannot start a ride that is not in status ACCEPTED!");
        }
        return ride;
    }
    public Ride acceptRide(Integer id){
        Ride ride =get(id);
        if (ride.getStatus() == RideStatus.PENDING) {
            ride.setStatus(RideStatus.ACCEPTED);
            save(ride);
        }
        else{
            throw new BadRequestException("Cannot accept a ride that is not in status PENDING!");
        }
        return ride;
    }
    public Ride endRide(Integer id){
        Ride ride =get(id);
        if (ride.getStatus() == RideStatus.ACTIVE) {
            ride.setStatus(RideStatus.FINISHED);
            save(ride);
        }
        else{
            throw new BadRequestException("Cannot accept a ride that is not in status FINISHED!");
        }
        return ride;
    }
    public Ride rejectRide(Integer id,String reason){
        Ride ride =get(id);
        if (ride.getStatus() == RideStatus.PENDING) {
            ride.setStatus(RideStatus.REJECTED);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            Rejection rejection =new Rejection(ride,passengerService.get(user.getId()),reason,LocalDateTime.now());
            ride.setRejection(rejection);
            save(ride);
        }
        else{
            throw new BadRequestException("Cannot accept a ride that is not in status PENDING!");
        }
        return ride;
    }
}
