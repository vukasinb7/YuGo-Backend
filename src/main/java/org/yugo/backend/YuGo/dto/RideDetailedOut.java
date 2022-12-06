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
public class RideDetailedOut {
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
    private boolean babyTransport;
    @Getter @Setter
    private boolean petTransport;
    @Getter @Setter
    private RejectionOut rejection;
    @Getter @Setter
    private String status;

    public RideDetailedOut(Ride ride) {
        this.id= ride.getId();
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getTotalCost();
        this.driver = UserSimplifiedMapper.fromUsertoDTO(ride.getDriver());
        this.passengers = ride.getPassengers().stream().map(UserSimplifiedMapper::fromUsertoDTO).toList();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.vehicleType = ride.getDriver().getVehicle().getVehicleType().toString();
        this.babyTransport = ride.getBabyTransport();
        this.petTransport = ride.getPetTransport();
        this.locations = ride.getLocations().stream().map(PathMapper::fromPathtoDTO).toList();
        this.status = ride.getStatus().toString();
        if (ride.getRejection()!=null)
            this.rejection=new RejectionOut(ride.getRejection());
    }
}
