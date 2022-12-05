package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.LocationMapper;
import org.yugo.backend.YuGo.mapper.UserSimplifiedMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class RideOut {
    @Getter @Setter
    private LocalDateTime startTime;

    @Getter @Setter
    private LocalDateTime endTime;

    @Getter @Setter
    private Double totalCost;

    @Getter @Setter
    private UserSimplifiedOut driver;

    @Getter @Setter
    private List<UserSimplifiedOut> passengers;

    @Getter @Setter
    private int estimatedTimeInMinutes;

    @Getter @Setter
    private String vehicleType;

    @Getter @Setter
    boolean babyTransport;

    @Getter @Setter
    boolean petTransport;

    @Getter @Setter
    private List<LocationInOut> address;

    @Getter @Setter
    private String status;

    public RideOut(LocalDateTime startTime, LocalDateTime endTime, Double totalCost, UserSimplifiedOut driver, List<UserSimplifiedOut> passengers, int estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, List<LocationInOut> address, String status) {
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
    public RideOut(Ride ride) {
        UserSimplifiedOut driver=new UserSimplifiedOut(ride.getDriver());
        List<UserSimplifiedOut> passengers= ride.getPassengers().stream().map(UserSimplifiedMapper::fromUsertoDTO).toList();
        List<LocationInOut> locations= ride.getPaths().stream().map(LocationMapper::fromLocationtoDTO).toList();
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
