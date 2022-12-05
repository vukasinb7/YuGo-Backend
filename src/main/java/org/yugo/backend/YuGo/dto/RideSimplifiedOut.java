package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.UserSimplifiedMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class RideSimplifiedOut {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private List<PathInOut> locations;
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

    public RideSimplifiedOut(Ride ride) {
        this.id = ride.getId();
        this.locations = ride.getPaths().stream().map(PathMapper::fromPathtoDTO).toList();
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getPrice();
        this.driver =new UserSimplifiedOut(ride.getDriver());
        this.passengers = ride.getPassengers().stream().map(UserSimplifiedMapper::fromUsertoDTO).toList();
        this.estimatedTimeInMinutes = ride.getEstimatedTime();
        this.vehicleType = ride.getDriver().getVehicle().getVehicleCategory().toString();
        this.babyTransport = ride.getIncludesBabies();
        this.petTransport = ride.getIncludesPets();
    }
}
