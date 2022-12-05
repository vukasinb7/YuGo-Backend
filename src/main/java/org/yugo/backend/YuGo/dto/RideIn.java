package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.UserSimplifiedMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.util.List;

public class RideIn {

    @Getter @Setter
    private List<LocationInOut> locations;
    @Getter @Setter
    private List<UserSimplifiedOut> passengers;
    @Getter @Setter
    private String vehicleType;
    @Getter @Setter
    private boolean babyTransport;
    @Getter @Setter
    private boolean petTransport;

    public RideIn(List<LocationInOut> locations, List<UserSimplifiedOut> passengers, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
