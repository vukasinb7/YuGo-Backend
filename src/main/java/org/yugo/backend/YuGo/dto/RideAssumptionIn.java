package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleCategory;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;

public class RideAssumptionIn {
    @Getter @Setter
    private List<PathInOut> locations;
    @Getter @Setter
    private VehicleCategory vehicleType;
    @Getter @Setter
    private boolean babyTransport;
    @Getter @Setter
    private boolean petTransport;

    public RideAssumptionIn(List<PathInOut> locations, VehicleCategory vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
