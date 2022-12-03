package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.UserRideResponseMapper;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class RideResponse {
    @Getter @Setter
    private LocalDateTime startTime;

    @Getter @Setter
    private LocalDateTime endTime;

    @Getter @Setter
    private Double totalCost;

    @Getter @Setter
    private UserRideResponse driver;

    @Getter @Setter
    private List<UserRideResponse> passengers;

    @Getter @Setter
    private int estimatedTimeInMinutes;

    @Getter @Setter
    private String vehicleType;

    @Getter @Setter
    boolean babyTransport;

    @Getter @Setter
    boolean petTransport;

    @Getter @Setter
    private List<LocationRespone> address;

    @Getter @Setter
    private String status;

    public RideResponse(LocalDateTime startTime, LocalDateTime endTime, Double totalCost, UserRideResponse driver, List<UserRideResponse> passengers, int estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, List<LocationRespone> address, String status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.address = address;
        this.status = status;
    }
    public RideResponse(Ride ride) {
        UserRideResponse driver=new UserRideResponse(ride.getDriver());
        List<UserRideResponse> passengers= ride.getPassengers().stream().map(UserRideResponseMapper::fromUsertoDTO).toList();
        List<LocationRespone> locations=List.of(new LocationRespone(ride.getPath().getStartingPoint()),new LocationRespone(ride.getPath().getDestination())) ;
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getPrice();
        this.driver = driver;
        this.passengers = passengers;
        this.estimatedTimeInMinutes = ride.getEstimatedTime();
        this.vehicleType = ride.getDriver().getVehicle().getVehicleCategory().toString();
        this.babyTransport = ride.getIncludesBabies();
        this.petTransport = ride.getIncludesPets();
        this.address = locations;
        this.status = ride.getStatus().toString();
    }
}
