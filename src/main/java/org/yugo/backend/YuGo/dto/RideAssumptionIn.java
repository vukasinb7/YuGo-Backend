package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;

import java.util.List;
@Getter @Setter
public class RideAssumptionIn {
    private List<PathInOut> locations;
    private VehicleType vehicleType;
    private boolean babyTransport;
    private boolean petTransport;

    public RideAssumptionIn(List<PathInOut> locations, VehicleType vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
