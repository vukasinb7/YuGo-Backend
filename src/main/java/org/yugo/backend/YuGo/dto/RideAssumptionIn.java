package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;

import java.util.List;

public class RideAssumptionIn {
    @Getter @Setter
    private List<PathInOut> locations;
    @Getter @Setter
    private VehicleType vehicleType;
    @Getter @Setter
    private boolean babyTransport;
    @Getter @Setter
    private boolean petTransport;

    public RideAssumptionIn(List<PathInOut> locations, VehicleType vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
